package cs.vsu.raspopov.carkit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private BrandDto brandDto;
    private ModelDto modelDto;
    private ModificationDto modificationDto;
    private BigDecimal mileage;
}
