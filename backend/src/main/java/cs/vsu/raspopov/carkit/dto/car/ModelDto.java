package cs.vsu.raspopov.carkit.dto.car;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@SuperBuilder
public class ModelDto {

    private String model;
    private Set<ModificationDto> modifications;
}
