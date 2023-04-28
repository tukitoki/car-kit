package cs.vsu.raspopov.carkit.service;

import cs.vsu.raspopov.carkit.dto.detail.*;
import cs.vsu.raspopov.carkit.entity.Detail;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface DetailService {

    void saveDetail(@NotNull DetailDto dto);

    DetailAddResponse showSaveDetail();

    DetailDto getById(@NotNull Long id);

    List<DetailMileageDto> getDetailsByMileage(DetailMileageRequest detailMileageRequest);

    Detail getDetailById(@NotNull Long id);
}
