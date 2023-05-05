package cs.vsu.raspopov.carkit.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor
public class RequestTime {

    private Long carId;
    private List<Long> details;
}
