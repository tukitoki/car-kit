package cs.vsu.raspopov.carkit.config;

import cs.vsu.raspopov.carkit.entity.DetailType;
import cs.vsu.raspopov.carkit.entity.enums.DetailEnum;
import cs.vsu.raspopov.carkit.repository.DetailTypeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@RequiredArgsConstructor
@Configuration
public class AppConfig {

    private final DetailTypeRepo detailTypeRepo;

    @Bean
    public void addDetailTypesToDB() {
        if (detailTypeRepo.getAllTypes() > 0) {
            return;
        }
        var types = Arrays.stream(DetailEnum.values()).toList();
        types.forEach(detailEnum -> {
            detailTypeRepo.save(new DetailType(detailEnum));
        });
    }
}
