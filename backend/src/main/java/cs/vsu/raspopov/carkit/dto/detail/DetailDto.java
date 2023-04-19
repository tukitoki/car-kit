package cs.vsu.raspopov.carkit.dto.detail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Super;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
    private Timestamp timeToDelivery;
    private String dimension;
    private List<Long> replacementIds;
    private String detailType;
}
