package cs.vsu.raspopov.carkit.service.impl;

import cs.vsu.raspopov.carkit.dto.maintenance_work.MaintenanceAddResponse;
import cs.vsu.raspopov.carkit.dto.maintenance_work.MaintenanceWorkDto;
import cs.vsu.raspopov.carkit.entity.Dimension;
import cs.vsu.raspopov.carkit.entity.MaintenanceWork;
import cs.vsu.raspopov.carkit.mapper.MaintenanceWorkMapper;
import cs.vsu.raspopov.carkit.repository.DetailTypeRepo;
import cs.vsu.raspopov.carkit.repository.DimensionRepo;
import cs.vsu.raspopov.carkit.repository.MaintenanceWorkRepo;
import cs.vsu.raspopov.carkit.service.CarService;
import cs.vsu.raspopov.carkit.service.MaintenanceWorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MaintenanceWorkServiceImpl implements MaintenanceWorkService {

    private final MaintenanceWorkMapper maintenanceWorkMapper;
    private final CarService carService;
    private final MaintenanceWorkRepo maintenanceWorkRepo;
    private final DimensionRepo dimensionRepo;
    private final DetailTypeRepo detailTypeRepo;

    @Override
    public void addCarWork(Long id, MaintenanceWorkDto dto) {
        var car = carService.getCar(id);

        var dimension = getDimensionByName(dto.getDimension());
        var detailType = detailTypeRepo.findByDisplayName(dto.getDetailType())
                .orElseThrow();

        var work = maintenanceWorkMapper.toEntity(dto, dimension, car.getId(), detailType.getId());
        maintenanceWorkRepo.save(work);
    }

    @Override
    public MaintenanceAddResponse showAddCarWork() {
        List<String> types = new LinkedList<>();
        detailTypeRepo.findAll().forEach(detailType -> {
            types.add(detailType.getDisplayName());
        });

        List<String> dimensions = new LinkedList<>();
        dimensionRepo.findAll().forEach(dimension -> {
            dimensions.add(dimension.getDimensionName());
        });

        return MaintenanceAddResponse.builder()
                .detailTypes(types)
                .dimensions(dimensions)
                .build();
    }

    @Override
    public MaintenanceWork getMaintenanceWork(Long carId, Long detailTypeId) {
        return maintenanceWorkRepo
                .findById_CarIdAndId_DetailTypeId(carId, detailTypeId)
                .orElseThrow();
    }

    @Override
    public LocalTime calcTimeByCarId(Long carId, Long detailTypeId) {
        var maintenanceWorks = getMaintenanceWork(carId, detailTypeId);

        return maintenanceWorks.getTimeToChange();
    }

    private Dimension getDimensionByName(String name) {
        var optionalDimension = dimensionRepo.findByDimensionName(name);
        return optionalDimension.orElseGet(() -> dimensionRepo.save(Dimension.builder()
                .dimensionName(name)
                .build()));
    }
}
