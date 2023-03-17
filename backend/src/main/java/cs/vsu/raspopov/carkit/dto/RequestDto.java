package cs.vsu.raspopov.carkit.dto;

import cs.vsu.raspopov.carkit.entity.Detail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {

    private Integer id;
    private Timestamp startTime;
    private Timestamp endTime;
    private CarDto carDto;
    private List<Detail> details;
}
