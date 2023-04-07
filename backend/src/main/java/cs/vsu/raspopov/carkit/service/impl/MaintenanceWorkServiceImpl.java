package cs.vsu.raspopov.carkit.service.impl;

import cs.vsu.raspopov.carkit.dto.maintenance_work.MaintenanceWorkDto;
import cs.vsu.raspopov.carkit.entity.Dimension;
import cs.vsu.raspopov.carkit.entity.MaintenanceWork;
import cs.vsu.raspopov.carkit.mapper.MaintenanceWorkMapper;
import cs.vsu.raspopov.carkit.repository.CarRepo;
import cs.vsu.raspopov.carkit.repository.DimensionRepo;
import cs.vsu.raspopov.carkit.repository.MaintenanceWorkRepo;
import cs.vsu.raspopov.carkit.service.MaintenanceWorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MaintenanceWorkServiceImpl implements MaintenanceWorkService {

    private final MaintenanceWorkMapper maintenanceWorkMapper;
    private final MaintenanceWorkRepo maintenanceWorkRepo;
    private final CarRepo carRepo;
    private final DimensionRepo dimensionRepo;

    @Override
    public void addCarWork(Long id, MaintenanceWorkDto dto) {
        var optionalCar = carRepo.findById(id);
        if (optionalCar.isEmpty()) {
            throw new RuntimeException();
        }

        var car = optionalCar.get();
        var dimension = getDimensionByName(dto.getDimensionName());

        var work = maintenanceWorkMapper.toEntity(dto, dimension, car);
        maintenanceWorkRepo.save(work);
    }

    private Dimension getDimensionByName(String name) {
        var optionalDimension = dimensionRepo.findByDimensionName(name);
        return optionalDimension.orElseGet(() -> dimensionRepo.save(Dimension.builder()
                .dimensionName(name)
                .build()));
    }
}
