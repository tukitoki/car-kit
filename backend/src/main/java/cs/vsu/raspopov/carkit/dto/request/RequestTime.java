package cs.vsu.raspopov.carkit.dto.request;

import cs.vsu.raspopov.carkit.dto.detail.response.DetailResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RequestTime {

    private Long carId;
    private List<DetailResponse> details;
}
