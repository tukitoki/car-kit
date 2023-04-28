package cs.vsu.raspopov.carkit.service.impl;

import cs.vsu.raspopov.carkit.dto.maintenance_work.MaintenanceWorkDto;
import cs.vsu.raspopov.carkit.entity.Dimension;
import cs.vsu.raspopov.carkit.entity.enums.DetailEnum;
import cs.vsu.raspopov.carkit.mapper.MaintenanceWorkMapper;
import cs.vsu.raspopov.carkit.repository.CarRepo;
import cs.vsu.raspopov.carkit.repository.DetailTypeRepo;
import cs.vsu.raspopov.carkit.repository.DimensionRepo;
import cs.vsu.raspopov.carkit.repository.MaintenanceWorkRepo;
import cs.vsu.raspopov.carkit.service.CarService;
import cs.vsu.raspopov.carkit.service.MaintenanceWorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class MaintenanceWorkServiceImpl implements MaintenanceWorkService {

    private final MaintenanceWorkMapper maintenanceWorkMapper;
    private final CarService carService;
    private final MaintenanceWorkRepo maintenanceWorkRepo;
    private final CarRepo carRepo;
    private final DimensionRepo dimensionRepo;
    private final DetailTypeRepo detailTypeRepo;

    @Override
    public void addCarWork(Long id, MaintenanceWorkDto dto) {
        var car = carService.getCar(id);

        var dimension = getDimensionByName(dto.getDimension());
        var detailType = detailTypeRepo.findByName(DetailEnum.valueOf(dto.getDetailType()))
                .orElseThrow(() -> new NoSuchElementException(("")));

        var work = maintenanceWorkMapper.toEntity(dto, dimension, car.getId(), detailType.getId());
        maintenanceWorkRepo.save(work);
    }

    @Override
    public LocalTime calcTimeByCarId(Long carId, Long detailTypeId) {
        var maintenanceWorks = maintenanceWorkRepo
                .findAllById_CarIdAndId_DetailTypeId(carId, detailTypeId);

        return maintenanceWorks.getTimeToChange();
    }

    private Dimension getDimensionByName(String name) {
        var optionalDimension = dimensionRepo.findByDimensionName(name);
        return optionalDimension.orElseGet(() -> dimensionRepo.save(Dimension.builder()
                .dimensionName(name)
                .build()));
    }
}
