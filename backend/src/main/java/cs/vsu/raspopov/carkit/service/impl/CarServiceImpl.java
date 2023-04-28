package cs.vsu.raspopov.carkit.service.impl;

import cs.vsu.raspopov.carkit.dto.car.*;
import cs.vsu.raspopov.carkit.dto.detail.DetailMileageAdd;
import cs.vsu.raspopov.carkit.entity.*;
import cs.vsu.raspopov.carkit.entity.enums.DetailEnum;
import cs.vsu.raspopov.carkit.mapper.BrandMapper;
import cs.vsu.raspopov.carkit.mapper.CarMapper;
import cs.vsu.raspopov.carkit.repository.*;
import cs.vsu.raspopov.carkit.service.CarService;
import cs.vsu.raspopov.carkit.service.DetailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class CarServiceImpl implements CarService {

    private final DetailService detailService;
    private final CarMapper carMapper;
    private final CarRepo carRepo;
    private final BrandRepo brandRepo;
    private final ModelRepo modelRepo;
    private final ModificationRepo modificationRepo;
    private final DetailMileageChangeRepo detailMileageChangeRepo;
    private final DetailTypeRepo detailTypeRepo;
    private final BrandMapper brandMapper;

    @Override
    public void saveCar(CarDto dto) {
        var brand = getBrandByName(dto.getBrand());
        var model = getModelByName(dto.getModel());
        var modification = getModification(dto.getModificationDto());

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
    public List<BrandDto> getAllCars() {
        List<BrandDto> brandDtos = new LinkedList<>();
        brandRepo.findAll().forEach(brand -> {
            brandDtos.add(brandMapper.toDto(brand));
        });
        return brandDtos;
    }

    @Override
    public Car getCar(Long id) {
        return carRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException(""));
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

    private Model getModelByName(String name) {
        var optionalModel = modelRepo.findByName(name);
        return optionalModel.orElseGet(() -> Model.builder()
                .name(name)
                .modifications(new ArrayList<>())
                .build());
    }

    private Modification getModification(ModificationDto dto) {
        var optionalModification = modificationRepo.findByNameAndEngineModelAndYearFromAndYearTo(dto.getName(),
                dto.getEngineModel(), dto.getYearFrom(), dto.getYearTo());
        return optionalModification.orElseGet(() -> Modification.builder()
                .name(dto.getName())
                .engineModel(dto.getEngineModel())
                .yearFrom(dto.getYearFrom())
                .yearTo(dto.getYearTo())
                .details(new LinkedHashSet<>())
                .detailMileageChange(new LinkedList<>())
                .build());
    }
}
