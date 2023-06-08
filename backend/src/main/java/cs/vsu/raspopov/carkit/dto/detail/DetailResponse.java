package cs.vsu.raspopov.carkit.dto.detail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@SuperBuilder
public class DetailResponse {

    private Long id;
    private BigDecimal count;
    private String dimension;
    private String name;
}
