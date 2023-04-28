package cs.vsu.raspopov.carkit.dto.car;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class ModificationDto {

    private String name;
    private String engineModel;
    private Integer yearFrom;
    private Integer yearTo;
}
