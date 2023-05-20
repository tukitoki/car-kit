package cs.vsu.raspopov.carkit.dto.request;

import cs.vsu.raspopov.carkit.dto.car.CarDto;
import cs.vsu.raspopov.carkit.dto.detail.DetailResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class RequestDto {

    private Long id;
    private String startTime;
    private String endTime;
    private String phoneNumber;
    private CarDto carDto;
    private List<DetailResponse> details;
}
