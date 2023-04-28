package cs.vsu.raspopov.carkit.dto.car;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
public class CarDto {

    private Long id;
    private String brand;
    private String model;
    private ModificationDto modificationDto;
}
