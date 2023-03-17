package cs.vsu.raspopov.carkit.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModelDto {

    private Integer id;
    private String name;

    private List<ModificationDto> modifications;
}
