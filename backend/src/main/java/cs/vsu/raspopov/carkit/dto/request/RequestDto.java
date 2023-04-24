package cs.vsu.raspopov.carkit.dto.request;

import cs.vsu.raspopov.carkit.dto.maintenance_work.MaintenanceWorkDto;

import java.time.LocalDateTime;
import java.util.List;

public class RequestDto {

    private Long carId;
    private LocalDateTime startTime;
    private List<MaintenanceWorkDto> maintenanceWorks;
}
