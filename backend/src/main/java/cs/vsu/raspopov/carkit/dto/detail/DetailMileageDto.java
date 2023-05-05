package cs.vsu.raspopov.carkit.dto.detail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class DetailMileageDto {

    private List<DetailDto> detailsToChange;
    private List<DetailDto> otherDetails;
}
