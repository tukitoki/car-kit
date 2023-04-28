package cs.vsu.raspopov.carkit.service.impl;

import cs.vsu.raspopov.carkit.dto.request.RequestTime;
import cs.vsu.raspopov.carkit.dto.request.RequestTimeResponse;
import cs.vsu.raspopov.carkit.entity.Request;
import cs.vsu.raspopov.carkit.repository.RequestRepo;
import cs.vsu.raspopov.carkit.repository.ScheduleRepo;
import cs.vsu.raspopov.carkit.service.DetailService;
import cs.vsu.raspopov.carkit.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@RequiredArgsConstructor
@Service
public class RequestServiceImpl implements RequestService {

    private final MaintenanceWorkServiceImpl maintenanceWorkService;
    private final DetailService detailService;
    private final ScheduleRepo scheduleRepo;
    private final RequestRepo requestRepo;

    @Override
    public RequestTimeResponse infoRequest(RequestTime requestTime) {
        var sum = LocalTime.of(0, 0);

//        for (var detailDto : requestTime.getDetails()) {
//            var detail = detailService.getDetailById(detailDto.getId());
//            var maintenanceTime = maintenanceWorkService
//                    .calcTimeByCarId(requestTime.getCarId(), detail.getDetailType().getId());
//            sum = sum.plusHours(maintenanceTime.getHour())
//                    .plusMinutes(maintenanceTime.getMinute());
//        }
        var mapDate = getAllInDate(LocalTime.of(2, 30));
        return RequestTimeResponse.builder()
                .availableTime(mapDate)
                .build();
    }

    private Map<String, List<String>> getAllInDate(LocalTime timeToChange) {
        Map<String, List<String>> times = new HashMap<>();
        var requests = requestRepo.findAll();
        scheduleRepo.findAll().forEach(schedule -> {
            var availableTime = new ArrayList<LocalTime>();
            var todayRequests = new HashSet<Request>();
            var endTimeWork = LocalDateTime.of(schedule.getDate(), schedule.getEndWorkTime());
            requests.forEach(request -> {
                var date = request.getStartTime().toLocalDate();
                if (date.isEqual(schedule.getDate())) {
                    todayRequests.add(request);
                }
            });
            for (var time = schedule.getStartWorkTime();
                 time.isBefore(schedule.getEndWorkTime());
                 time = time.plusMinutes(15)) {
                var fullTimeToChange = time.plusHours(timeToChange.getHour())
                        .plusMinutes(timeToChange.getMinute());
                LocalDateTime startTime = LocalDateTime.of(schedule.getDate(), time);
                LocalDateTime endTime = LocalDateTime.of(schedule.getDate(), fullTimeToChange);
                if (isRightTime(todayRequests, startTime, endTime) && endTime.isBefore(endTimeWork)) {
                    availableTime.add(time);
                }
            }
            var stringTime = availableTime.stream().map(LocalTime::toString).toList();
            times.put(schedule.getDate().toString(), stringTime);
        });
        return times;
    }

    private boolean isRightTime(HashSet<Request> requests, LocalDateTime startTime, LocalDateTime endTime) {
        for (var request : requests) {
            var startTimeReq = request.getStartTime();
            var endTimeReq = request.getEndTime();

            if ((endTime.isAfter(startTimeReq) && endTime.isBefore(endTimeReq.plusMinutes(15)))
                    || (startTime.isAfter(startTimeReq) && startTime.isBefore(endTimeReq.plusMinutes(15)))) {
                return false;
            }
        }
        return true;
    }
}
