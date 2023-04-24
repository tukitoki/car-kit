package cs.vsu.raspopov.carkit.controller.api;

import cs.vsu.raspopov.carkit.dto.schedule.ScheduleShowRequest;
import cs.vsu.raspopov.carkit.dto.schedule.ScheduleShowResponse;

public interface ScheduleApi {

    ScheduleShowResponse getAvailableSchedule(ScheduleShowRequest dto);
}
