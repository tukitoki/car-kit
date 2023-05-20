package cs.vsu.raspopov.carkit.service.impl;

import cs.vsu.raspopov.carkit.dto.car.*;
import cs.vsu.raspopov.carkit.dto.detail.DetailDto;
import cs.vsu.raspopov.carkit.dto.detail.DetailMileageAdd;
import cs.vsu.raspopov.carkit.dto.page.PageModel;
import cs.vsu.raspopov.carkit.entity.*;
import cs.vsu.raspopov.carkit.mapper.*;
import cs.vsu.raspopov.carkit.repository.*;
import cs.vsu.raspopov.carkit.service.CarService;
import cs.vsu.raspopov.carkit.service.DetailService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class CarServiceImpl implements CarService {

    private final DetailService detailService;
    private final CarMapper carMapper;
    private final BrandMapper brandMapper;
    private final ModelMapper modelMapper;
    private final ModificationMapper modificationMapper;
    private final DetailMapper detailMapper;
    private final CarRepo carRepo;
    private final BrandRepo brandRepo;
    private final ModelRepo modelRepo;
    private final ModificationRepo modificationRepo;
    private final DetailMileageChangeRepo detailMileageChangeRepo;
    private final DetailTypeRepo detailTypeRepo;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public void saveCar(CarDto dto) {
        var brand = getBrandByName(dto.getBrand());
        var model = getModelByName(dto.getModel(), brand.getId() != null ? brand : null);
        var modification = getModification(dto.getModificationDto(), model.getId() != null ? model : null);

        brand.getModels().add(model);
        model.setBrand(brand);
        model.getModifications().add(modification);
        modification.setModel(model);

        var car = carMapper.toEntity(brand, model, modification);
        carRepo.save(car);
    }

    @Override
    public void addDetailsToCar(CarAddDetailsRequest dto, Long id) {
        var modification = getCar(id).getModification();
        dto.getDetailIds().forEach(detailId -> {
            var detail = detailService.getDetailById(detailId);
            modification.getDetails().add(detail);
        });
        modificationRepo.save(modification);
    }

    @Override
    public CarAddDetailsResponse showAddDetailsToCar(Long id) {
        return null;
    }

    @Override
    public CarDto getCarById(Long id) {
        var car = getCar(id);


        return null;
    }

    @Override
    public PageModel<BrandDto> getAllCars(int pageNumber, int pageSize, String brand, String model) {
        Page<Car> page = filerPage(pageNumber, pageSize, brand, model);

        List<BrandDto> brandDtos = new LinkedList<>();
        for (var car : page.getContent()) {
            var optionalBrand = brandDtos.stream()
                    .filter(brandDto -> brandDto.getBrand()
                            .equals(car.getBrand().getName()))
                    .findFirst();
            if (optionalBrand.isEmpty()) {
                var brandDto = brandMapper.toDto(car.getBrand(), car.getModel(), car.getModification());
                brandDtos.add(brandDto);
                continue;
            }

            var existBrand = optionalBrand.get();
            var optionalModel = existBrand.getModels()
                    .stream()
                    .filter(modelDto -> modelDto.getModel()
                            .equals(car.getModel().getName()))
                    .findFirst();
            if (optionalModel.isEmpty()) {
                existBrand.getModels().add(modelMapper.toDto(car.getModel(), car.getModification()));
                continue;
            }

            var existModel = optionalModel.get();
            existModel.getModifications().add(modificationMapper.toDto(car.getModification()));
        }

        return PageModel.of(brandDtos, pageNumber, page.getTotalElements(),
                pageSize, page.getTotalPages());
    }

    @Override
    public Car getCar(Long id) {
        return carRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException(""));
    }

    @Override
    public List<DetailDto> getCarDetails(Long id) {
        var car = getCar(id);
        List<DetailDto> details = new LinkedList<>();
        car.getModification().getDetails().forEach(detail -> {
            details.add(detailMapper.toDto(detail, null));
        });
        return details;
    }

    @Override
    @Transactional
    public void addMileageDetails(List<DetailMileageAdd> dto, Long id) {
        var car = getCar(id);

        dto.forEach(detailMileageAdd -> {
            var detailType = detailTypeRepo.findByDisplayName(detailMileageAdd.getDetailType())
                    .orElseThrow();
            detailMileageChangeRepo.save(DetailMileageChange.builder()
                    .mileage(detailMileageAdd.getMileage())
                    .detailType(detailType)
                    .modification(car.getModification())
                    .build());
        });
    }

    private Brand getBrandByName(String name) {
        var optionalBrand = brandRepo.findByName(name);
        return optionalBrand.orElseGet(() -> Brand.builder()
                .name(name)
                .models(new ArrayList<>())
                .build());
    }

    private Model getModelByName(String name, Brand brand) {
        var optionalModel = modelRepo.findByNameAndBrand(name, brand);
        return optionalModel.orElseGet(() -> Model.builder()
                .name(name)
                .modifications(new ArrayList<>())
                .build());
    }

    private Modification getModification(ModificationDto dto, Model model) {
        var optionalModification = modificationRepo.findByNameAndEngineModelAndYearFromAndYearToAndModel(dto.getName(),
                dto.getEngineModel(), dto.getYearFrom(), dto.getYearTo(), model);
        return optionalModification.orElseGet(() -> Modification.builder()
                .name(dto.getName())
                .engineModel(dto.getEngineModel())
                .yearFrom(dto.getYearFrom())
                .yearTo(dto.getYearTo())
                .details(new LinkedHashSet<>())
                .detailMileageChange(new LinkedList<>())
                .build());
    }

    private Page<Car> filerPage(int pageNumber, int pageSize,
                                String brand, String model) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> query = cb.createQuery(Car.class);

        Root<Car> root = query.from(Car.class);
        Join<Car, Brand> brands = root.join("brand");
        Join<Car, Model> models = root.join("model");

        query.select(root).distinct(true);

        List<Predicate> predicates = new ArrayList<>();
        if (brand != null && !brand.equals("")) {
            predicates.add(cb.like(cb.lower(brands.get("name")), "%" + brand.toLowerCase() + "%"));
        }
        if (model != null && !model.equals("")) {
            predicates.add(cb.like(cb.lower(models.get("name")), "%" + model.toLowerCase() + "%"));
        }

        query.where(cb.and(predicates.toArray(new Predicate[0])));

        query.orderBy(cb.asc(root.get("id")));

        TypedQuery<Car> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<Car> cars = typedQuery.getResultList();
        long count = countFilteredCars(brand, model);

        return new PageImpl<>(cars, pageable, count);
    }


    private long countFilteredCars(String brand, String model) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);

        Root<Car> root = query.from(Car.class);
        Join<Car, Brand> brands = root.join("brand");
        Join<Car, Model> models = root.join("model");

        query.select(cb.countDistinct(root));

        List<Predicate> predicates = new ArrayList<>();
        if (brand != null && !brand.equals("")) {
            predicates.add(cb.like(cb.lower(brands.get("name")), "%" + brand.toLowerCase() + "%"));
        }
        if (model != null && !model.equals("")) {
            predicates.add(cb.like(cb.lower(models.get("name")), "%" + model.toLowerCase() + "%"));
        }

        query.where(cb.and(predicates.toArray(new Predicate[0])));

        TypedQuery<Long> typedQuery = entityManager.createQuery(query);

        return typedQuery.getSingleResult();
    }
}
