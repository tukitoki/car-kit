package cs.vsu.raspopov.carkit.service;

import cs.vsu.raspopov.carkit.dto.maintenance_work.MaintenanceWorkDto;

public interface MaintenanceWorkService {

    void addCarWork(Long id, MaintenanceWorkDto dto);
}
