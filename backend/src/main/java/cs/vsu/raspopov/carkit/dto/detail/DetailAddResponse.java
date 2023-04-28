package cs.vsu.raspopov.carkit.dto.detail;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class DetailAddResponse {

    private List<DetailResponse> details;
    private List<String> detailTypes;
    private List<String> dimensions;
}
