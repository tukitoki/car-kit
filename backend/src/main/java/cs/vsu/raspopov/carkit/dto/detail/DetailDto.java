package cs.vsu.raspopov.carkit.dto.detail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
@SuperBuilder
public class DetailDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String producer;
    private String timeToDelivery;
    private String dimension;
    private BigDecimal count;
    private List<DetailResponse> detailResponses;
    private String detailType;
}
