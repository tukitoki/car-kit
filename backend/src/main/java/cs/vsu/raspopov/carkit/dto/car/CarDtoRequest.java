package cs.vsu.raspopov.carkit.dto.car;

import cs.vsu.raspopov.carkit.dto.ModificationDtoRequest;
import lombok.Getter;

@Getter
public class CarDtoRequest {

    private String brand;
    private String modelName;
    private ModificationDtoRequest modificationDto;
}
