package cs.vsu.raspopov.carkit.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {

    private Integer id;

    private LocalDate dayOfWeek;

    private Timestamp startWorkTime;

    private Timestamp endWorkTime;
}
