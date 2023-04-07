package cs.vsu.raspopov.carkit.controller.api;

import cs.vsu.raspopov.carkit.dto.maintenance_work.MaintenanceWorkDto;
import cs.vsu.raspopov.carkit.entity.MaintenanceWork;

public interface MaintenanceWorkApi {

    void addCarWork(Long id, MaintenanceWorkDto dto);
}
