package cs.vsu.raspopov.carkit.dto.car;

import cs.vsu.raspopov.carkit.dto.ModificationDto;
import cs.vsu.raspopov.carkit.dto.detail.DetailDto;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
public class CarDtoResponse {

    private String brand;
    private String modelName;
    private ModificationDto modificationDto;
}
