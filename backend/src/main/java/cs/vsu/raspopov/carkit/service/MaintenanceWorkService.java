package cs.vsu.raspopov.carkit.service;

import cs.vsu.raspopov.carkit.dto.maintenance_work.MaintenanceAddResponse;
import cs.vsu.raspopov.carkit.dto.maintenance_work.MaintenanceWorkDto;
import cs.vsu.raspopov.carkit.entity.MaintenanceWork;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.time.LocalTime;

@Validated
public interface MaintenanceWorkService {

    void addCarWork(@NotNull Long id, @NotNull MaintenanceWorkDto dto);

    MaintenanceAddResponse showAddCarWork();

    MaintenanceWork getMaintenanceWork(@NotNull Long carId, @NotNull Long detailTypeId);

    LocalTime calcTimeByCarId(@NotNull Long carId, @NotNull Long detailTypeId);
}
