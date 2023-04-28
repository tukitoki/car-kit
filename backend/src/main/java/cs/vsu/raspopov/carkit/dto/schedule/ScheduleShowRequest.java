package cs.vsu.raspopov.carkit.dto.schedule;

import cs.vsu.raspopov.carkit.dto.detail.DetailResponse;
import cs.vsu.raspopov.carkit.dto.maintenance_work.MaintenanceWorkDto;

import java.util.List;

public class ScheduleShowRequest {

    private List<DetailResponse> detailResponses;
    private Long carId;
    private List<MaintenanceWorkDto> maintenanceWorks;
}
