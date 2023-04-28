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

//    @Bean
//    public void initShop() {
//        var scheduleList = new ArrayList<Schedule>();
//        scheduleList.add(Schedule.builder()
//                .date(LocalDate.now())
//                .startWorkTime(LocalTime.parse(startTimeWork))
//                .endWorkTime(LocalTime.parse(endTimeWork))
//                .build());
//        scheduleList.add(Schedule.builder()
//                .date(LocalDate.now().plusDays(1))
//                .startWorkTime(LocalTime.parse(startTimeWork))
//                .endWorkTime(LocalTime.parse(endTimeWork))
//                .build());
//        var shop = AutoRepairShop.builder()
//                .name("master")
//                .address("dfgfdg")
//                .schedule(scheduleList)
//                .build();
//        autoRepairShopRepo.save(shop);
//    }
}
