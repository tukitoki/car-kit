package cs.vsu.raspopov.carkit.service.impl;

import cs.vsu.raspopov.carkit.dto.schedule.ScheduleShowDtoResponse;
import cs.vsu.raspopov.carkit.entity.AutoRepairShop;
import cs.vsu.raspopov.carkit.entity.Request;
import cs.vsu.raspopov.carkit.entity.Schedule;
import cs.vsu.raspopov.carkit.repository.AutoRepairShopRepo;
import cs.vsu.raspopov.carkit.repository.RequestRepo;
import cs.vsu.raspopov.carkit.repository.ScheduleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ScheduleServiceImpl {

    private final AutoRepairShopRepo autoRepairShopRepo;
    private final ScheduleRepo scheduleRepo;
    private final RequestRepo requestRepo;

    public Schedule getSchedule(Long repairShopId) {
        var shop = autoRepairShopRepo.findById(repairShopId)
                .orElseThrow(() -> new NoSuchElementException("fdf"));

        LocalDate currDate = LocalDate.now();
        shop.getSchedule().forEach(schedule -> {
            if (schedule.getDate().isBefore(currDate)) {
                scheduleRepo.delete(schedule);
                var newSchedule = Schedule.builder()
                        .autoRepairShop(shop)
                        .date(currDate.plusDays(14))
                        .startWorkTime(schedule.getStartWorkTime())
                        .endWorkTime(schedule.getEndWorkTime())
                        .build();
                scheduleRepo.save(newSchedule);
            }
        });


        return null;
    }

    private ScheduleShowDtoResponse fillScheduleOnDay(List<Request> requests, LocalDate date,
                                                      Time startWork, Time endWork) {
        return null;
    }

    private List<Request> getRequestsByDate(AutoRepairShop shop, LocalDate date) {
        List<Request> requests = new ArrayList<>();
        shop.getRequests().forEach(request -> {
            if (request.getDate().isEqual(date)) {
                requests.add(request);
            }
        });
        return requests;
    }
}
