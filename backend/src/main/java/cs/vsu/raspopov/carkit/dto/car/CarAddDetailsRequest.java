package cs.vsu.raspopov.carkit.dto.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class CarAddDetailsRequest {

    private List<Long> detailIds;
}
