package cs.vsu.raspopov.carkit.mapper;

import cs.vsu.raspopov.carkit.dto.maintenance_work.MaintenanceWorkDto;
import cs.vsu.raspopov.carkit.entity.Car;
import cs.vsu.raspopov.carkit.entity.Dimension;
import cs.vsu.raspopov.carkit.entity.MaintenanceWork;
import org.springframework.stereotype.Component;

@Component
public class MaintenanceWorkMapper {

    public MaintenanceWork toEntity(MaintenanceWorkDto dto, Dimension dimension, Car car) {
        return MaintenanceWork.builder()
                .detailEnum(dto.getDetailEnum())
                .dimension(dimension)
                .timeToChange(dto.getTimeToChange())
                .car(car)
                .build();
    }
}
