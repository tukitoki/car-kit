package cs.vsu.raspopov.carkit.service;

import cs.vsu.raspopov.carkit.dto.maintenance_work.MaintenanceWorkDto;

import java.time.LocalTime;

public interface MaintenanceWorkService {

    void addCarWork(Long id, MaintenanceWorkDto dto);

    LocalTime calcTimeByCarId(Long carId, Long detailTypeId);
}
