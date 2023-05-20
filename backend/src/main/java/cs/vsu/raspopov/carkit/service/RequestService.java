package cs.vsu.raspopov.carkit.service;

import cs.vsu.raspopov.carkit.dto.car.BrandDto;
import cs.vsu.raspopov.carkit.dto.page.PageModel;
import cs.vsu.raspopov.carkit.dto.page.SortOrder;
import cs.vsu.raspopov.carkit.dto.request.RequestApplyResponse;
import cs.vsu.raspopov.carkit.dto.request.RequestTime;
import cs.vsu.raspopov.carkit.dto.request.RequestTimeResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface RequestService {

    PageModel<BrandDto> getAllCars(int pageNumber, int pageSize, SortOrder order);


    void applyRequest(@NotNull RequestApplyResponse requestApply);

    RequestTimeResponse infoRequest(@NotNull RequestTime requestTime);
}
