package cs.vsu.raspopov.carkit.config;

import cs.vsu.raspopov.carkit.entity.AutoRepairShop;
import cs.vsu.raspopov.carkit.entity.DetailType;
import cs.vsu.raspopov.carkit.entity.Schedule;
import cs.vsu.raspopov.carkit.entity.enums.DetailEnum;
import cs.vsu.raspopov.carkit.repository.AutoRepairShopRepo;
import cs.vsu.raspopov.carkit.repository.DetailTypeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class AppConfig {

    @Value("${repair-shop.startTimeWork}")
    private String startTimeWork;
    @Value("${repair-shop.endTimeWork}")
    private String endTimeWork;
    private final DetailTypeRepo detailTypeRepo;
    private final AutoRepairShopRepo autoRepairShopRepo;

    @Bean
    public void addDetailTypesToDB() {
        if (detailTypeRepo.getAllTypes() > 0) {
            return;
        }
        var types = Arrays.stream(DetailEnum.values()).toList();
        types.forEach(detailEnum -> {
            detailTypeRepo.save(new DetailType(detailEnum, detailEnum.toString()));
        });
    }

    @Bean
    public void initShop() {
        if (autoRepairShopRepo.findById(1L).isPresent()) {
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
            return;
        }
        var shop = AutoRepairShop.builder()
                .name("AutoRepairShop")
                .address("ул. Минская д. 120")
                .build();

        var scheduleList = new ArrayList<Schedule>();
        for (var plusDate = 0; plusDate < 14; plusDate++) {
            scheduleList.add(Schedule.builder()
                    .date(LocalDate.now().plusDays(plusDate))
                    .startWorkTime(LocalTime.parse(startTimeWork))
                    .endWorkTime(LocalTime.parse(endTimeWork))
                    .autoRepairShop(shop)
                    .build());
        }

        shop.setSchedule(scheduleList);
        autoRepairShopRepo.save(shop);
    }
}
