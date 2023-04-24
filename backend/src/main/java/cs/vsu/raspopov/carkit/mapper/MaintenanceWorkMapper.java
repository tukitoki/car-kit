package cs.vsu.raspopov.carkit.mapper;

import cs.vsu.raspopov.carkit.dto.maintenance_work.MaintenanceWorkDto;
import cs.vsu.raspopov.carkit.entity.Car;
import cs.vsu.raspopov.carkit.entity.Dimension;
import cs.vsu.raspopov.carkit.entity.MaintenanceWork;
import cs.vsu.raspopov.carkit.entity.MaintenanceWorkId;
import org.springframework.stereotype.Component;

@Component
public class MaintenanceWorkMapper {

    public MaintenanceWork toEntity(MaintenanceWorkDto dto, Dimension dimension, Long carId, Long detailTypeId) {
        return MaintenanceWork.builder()
                .id(new MaintenanceWorkId(carId, detailTypeId))
                .dimension(dimension)
                .timeToChange(dto.getTimeToChange())
                .build();
    }
}
