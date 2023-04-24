package cs.vsu.raspopov.carkit.dto.maintenance_work;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Getter
@NoArgsConstructor
public class MaintenanceWorkDto {

    private String detailType;
    private String dimension;
    private Time timeToChange;
}
