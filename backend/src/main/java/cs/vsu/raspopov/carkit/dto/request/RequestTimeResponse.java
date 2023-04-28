package cs.vsu.raspopov.carkit.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@SuperBuilder
public class RequestTimeResponse {

    Map<String, List<String>> availableTime;
}
