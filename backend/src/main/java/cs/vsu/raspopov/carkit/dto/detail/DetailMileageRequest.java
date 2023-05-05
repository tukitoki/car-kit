package cs.vsu.raspopov.carkit.dto.detail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailMileageRequest {

    private Integer mileage;
    private Long carId;
}
