package cs.vsu.raspopov.carkit.dto;

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
public class ModificationDto {

    private Integer id;
    private String name;
    private String engineModel;
    private Timestamp yearFrom;
    private Timestamp yearTo;
    private List<DetailDto> details;
}
