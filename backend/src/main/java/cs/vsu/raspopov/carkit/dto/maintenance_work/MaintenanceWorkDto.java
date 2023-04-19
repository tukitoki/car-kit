package cs.vsu.raspopov.carkit.dto.maintenance_work;

import cs.vsu.raspopov.carkit.entity.enums.DetailEnum;
import lombok.Getter;

import java.sql.Time;

@Getter
public class MaintenanceWorkDto {

    private DetailEnum detailEnum;
    private String dimensionName;
    private Time timeToChange;
}
