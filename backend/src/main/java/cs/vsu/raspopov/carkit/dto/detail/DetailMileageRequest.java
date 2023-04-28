package cs.vsu.raspopov.carkit.dto.detail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DetailMileageRequest {

    private Integer mileage;
    private Long carId;
}
