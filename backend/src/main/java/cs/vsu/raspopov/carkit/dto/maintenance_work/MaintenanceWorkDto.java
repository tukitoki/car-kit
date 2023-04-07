package cs.vsu.raspopov.carkit.dto.maintenance_work;

import cs.vsu.raspopov.carkit.entity.Dimension;
import cs.vsu.raspopov.carkit.entity.enums.DetailType;
import lombok.Getter;

import java.sql.Time;

@Getter
public class MaintenanceWorkDto {

    private DetailType detailType;
    private String dimensionName;
    private Time timeToChange;
}
