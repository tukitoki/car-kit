package cs.vsu.raspopov.carkit.dto.car;

import cs.vsu.raspopov.carkit.dto.ModificationDto;
import cs.vsu.raspopov.carkit.dto.detail.DetailDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@SuperBuilder
public class CarAddDetailsResponse {

    private String brand;
    private String modelName;
    private ModificationDto modificationDto;
    private List<DetailDto> details;
}
