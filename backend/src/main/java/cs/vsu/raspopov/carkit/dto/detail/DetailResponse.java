package cs.vsu.raspopov.carkit.dto.detail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class DetailResponse {

    private Long id;
    private String name;
}
