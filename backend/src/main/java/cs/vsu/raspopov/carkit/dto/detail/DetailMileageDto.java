package cs.vsu.raspopov.carkit.dto.detail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class DetailMileageDto {

    private BigDecimal countToChange;
    private String detailType;
    private List<DetailDto> detailsToChange;
    private List<DetailDto> otherDetails;
}
