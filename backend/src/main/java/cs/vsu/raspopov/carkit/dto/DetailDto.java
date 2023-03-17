package cs.vsu.raspopov.carkit.dto;

import cs.vsu.raspopov.carkit.entity.enums.DetailType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailDto {

    private Integer id;

    private String name;

    private String description;

    private BigDecimal price;

    private String producer;

    private Timestamp timeToChange;

    private Timestamp timeToDelivery;

    private DimensionDto dimension;

    private List<ReplacementDto> replacements;

    private DetailType detailType;
}
