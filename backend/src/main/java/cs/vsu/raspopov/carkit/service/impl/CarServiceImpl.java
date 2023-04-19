package cs.vsu.raspopov.carkit.service.impl;

import cs.vsu.raspopov.carkit.dto.CarDto;
import cs.vsu.raspopov.carkit.dto.ModificationDto;
import cs.vsu.raspopov.carkit.dto.car.BrandDto;
import cs.vsu.raspopov.carkit.dto.car.CarDtoResponse;
import cs.vsu.raspopov.carkit.dto.car.response.CarAddResponse;
import cs.vsu.raspopov.carkit.entity.Brand;
import cs.vsu.raspopov.carkit.entity.Model;
import cs.vsu.raspopov.carkit.entity.Modification;
import cs.vsu.raspopov.carkit.mapper.CarMapper;
import cs.vsu.raspopov.carkit.repository.*;
import cs.vsu.raspopov.carkit.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class CarServiceImpl implements CarService {

    private final CarMapper carMapper;
    private final CarRepo carRepo;
    private final BrandRepo brandRepo;
    private final DetailRepo detailRepo;
    private final ModelRepo modelRepo;
    private final ModificationRepo modificationRepo;

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
    public CarAddResponse showSaveCar() {
        ArrayList<BrandDto> brandDtos = new ArrayList<>();
        brandRepo.findAll().forEach(brand -> {
            brandDtos.add(BrandDto.builder()
                    .brand(brand.getName())
                    .models(brand.getModels().stream()
                            .map(Model::getName)
                            .toList())
                    .build());
        });
        return CarAddResponse.builder()
                .brandDtos(brandDtos)
                .build();
    }

    @Override
    public CarDtoResponse getCarById(Long id) {
        return null;
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
                .build());
    }
}
