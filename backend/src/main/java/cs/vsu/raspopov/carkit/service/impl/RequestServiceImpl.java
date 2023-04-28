package cs.vsu.raspopov.carkit.service.impl;

import cs.vsu.raspopov.carkit.dto.request.RequestApplyResponse;
import cs.vsu.raspopov.carkit.dto.request.RequestTime;
import cs.vsu.raspopov.carkit.dto.request.RequestTimeResponse;
import cs.vsu.raspopov.carkit.entity.MaintenanceWork;
import cs.vsu.raspopov.carkit.entity.Request;
import cs.vsu.raspopov.carkit.repository.RequestRepo;
import cs.vsu.raspopov.carkit.repository.ScheduleRepo;
import cs.vsu.raspopov.carkit.service.CarService;
import cs.vsu.raspopov.carkit.service.DetailService;
import cs.vsu.raspopov.carkit.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@RequiredArgsConstructor
@Service
public class RequestServiceImpl implements RequestService {

    private final MaintenanceWorkServiceImpl maintenanceWorkService;
    private final CarService carService;
    private final DetailService detailService;
    private final ScheduleRepo scheduleRepo;
    private final RequestRepo requestRepo;

    @Override
    public void applyRequest(RequestApplyResponse requestApply) {
        List<MaintenanceWork> works = new ArrayList<>();
        var details = requestApply.getRequestTime().getDetails()
                .stream()
                .map(id -> {
                    var detail = detailService.getDetailById(id);

                    works.add(maintenanceWorkService.getMaintenanceWork(requestApply.getRequestTime().getCarId(),
                            detail.getDetailType().getId()));

                    return detail;
                })
                .toList();
        var date = LocalDate.parse(requestApply.getDate());
        var startTime = LocalTime.parse(requestApply.getStartTime());
        var endTime = getSumTime(requestApply.getRequestTime().getDetails(),
                requestApply.getRequestTime().getCarId());
        endTime = endTime.plusHours(startTime.getHour())
                .plusMinutes(startTime.getMinute());

        var car = carService.getCar(requestApply.getRequestTime().getCarId());

        requestRepo.save(Request.builder()
                .startTime(LocalDateTime.of(date, startTime))
                .endTime(LocalDateTime.of(date, endTime))
                .phoneNumber(requestApply.getPhoneNumber())
                .details(details)
                .maintenanceWork(works)
                .car(car)
                .build());
    }

    @Override
    public RequestTimeResponse infoRequest(RequestTime requestTime) {
        var sum = getSumTime(requestTime.getDetails(), requestTime.getCarId());

        var mapDate = getAllInDate(sum);
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

            var stringTime = availableTime
                    .stream()
                    .map(LocalTime::toString)
                    .toList();
            times.put(schedule.getDate().toString(), stringTime);
        });
        return times;
    }

    private LocalTime getSumTime(List<Long> detailIds, Long carId) {
        var sum = LocalTime.of(0, 0);

        for (var detailId : detailIds) {
            var detail = detailService.getDetailById(detailId);
            var maintenanceTime = maintenanceWorkService
                    .calcTimeByCarId(carId, detail.getDetailType().getId());
            sum = sum.plusHours(maintenanceTime.getHour())
                    .plusMinutes(maintenanceTime.getMinute());
        }
        return sum;
    }

    private boolean isRightTime(HashSet<Request> requests,
                                LocalDateTime startTime, LocalDateTime endTime) {
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
