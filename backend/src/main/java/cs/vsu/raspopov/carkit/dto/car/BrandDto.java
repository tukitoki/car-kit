package cs.vsu.raspopov.carkit.dto.car;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class BrandDto {

    private String brand;
    private List<String> models;
}
