package cs.vsu.raspopov.carkit.dto.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor
public class ScheduleShowDtoResponse {

    private LocalDate date;
    private List<Time> availableTime;
}
