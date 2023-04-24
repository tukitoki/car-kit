package cs.vsu.raspopov.carkit.dto.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@SuperBuilder
@NoArgsConstructor
public class ScheduleShowResponse {

    private Map<LocalDate, List<LocalDateTime>> availableTime;

}
