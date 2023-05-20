package cs.vsu.raspopov.carkit.config;

import cs.vsu.raspopov.carkit.entity.Schedule;
import cs.vsu.raspopov.carkit.repository.AutoRepairShopRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@EnableScheduling
@Configuration
public class ScheduleConfig {

    @Value("${repair-shop.startTimeWork}")
    private String startTimeWork;
    @Value("${repair-shop.endTimeWork}")
    private String endTimeWork;
    private final AutoRepairShopRepo autoRepairShopRepo;

    @Scheduled(cron = "@midnight")
    public void scheduleClear() {
        if (autoRepairShopRepo.findById(1L).isEmpty()) {
            return;
        }
        var repairShop = autoRepairShopRepo.findById(1L).get();
        var schedule = repairShop.getSchedule();

        List<Schedule> toRemoveSchedule = new ArrayList<>();
        for (var day : schedule) {
            if (LocalDate.now().isAfter(day.getDate())) {
                toRemoveSchedule.add(day);
            } else {
                return;
            }
        }
        for (int plusDate = 0; plusDate < toRemoveSchedule.size(); plusDate++) {
            schedule.remove(toRemoveSchedule.get(plusDate));
            schedule.add(Schedule.builder()
                    .date(LocalDate.now().plusDays(14 - plusDate))
                    .startWorkTime(LocalTime.parse(startTimeWork))
                    .endWorkTime(LocalTime.parse(endTimeWork))
                    .autoRepairShop(repairShop)
                    .build());
        }

        repairShop.setSchedule(schedule);
        autoRepairShopRepo.save(repairShop);
    }
}
