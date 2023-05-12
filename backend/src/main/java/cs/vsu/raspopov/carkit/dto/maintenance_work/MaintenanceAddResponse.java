package cs.vsu.raspopov.carkit.dto.maintenance_work;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@NoArgsConstructor
@SuperBuilder
public class MaintenanceAddResponse {

    private List<String> detailTypes;
    private List<String> dimensions;
}
