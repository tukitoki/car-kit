package cs.vsu.raspopov.carkit.dto.car.response;

import cs.vsu.raspopov.carkit.dto.car.BrandDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class CarAddResponse {

    private List<BrandDto> brandDtos;
}
