package cs.vsu.raspopov.carkit.dto.detail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class DetailMileageAdd {

    private Integer mileage;
    private BigDecimal count;
    private String detailType;
    private String dimension;
}
