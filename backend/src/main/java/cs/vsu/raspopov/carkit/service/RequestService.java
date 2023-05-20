package cs.vsu.raspopov.carkit.service;

import cs.vsu.raspopov.carkit.dto.page.PageModel;
import cs.vsu.raspopov.carkit.dto.page.SortDirection;
import cs.vsu.raspopov.carkit.dto.request.RequestApplyResponse;
import cs.vsu.raspopov.carkit.dto.request.RequestDto;
import cs.vsu.raspopov.carkit.dto.request.RequestTime;
import cs.vsu.raspopov.carkit.dto.request.RequestTimeResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface RequestService {

    PageModel<RequestDto> getAllCars(int pageNumber, int pageSize, SortDirection order);


    void applyRequest(@NotNull RequestApplyResponse requestApply);

    RequestTimeResponse infoRequest(@NotNull RequestTime requestTime);
}
