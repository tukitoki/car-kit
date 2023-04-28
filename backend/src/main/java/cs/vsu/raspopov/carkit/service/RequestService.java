package cs.vsu.raspopov.carkit.service;

import cs.vsu.raspopov.carkit.dto.request.RequestApplyResponse;
import cs.vsu.raspopov.carkit.dto.request.RequestTime;
import cs.vsu.raspopov.carkit.dto.request.RequestTimeResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface RequestService {

    void applyRequest(@NotNull RequestApplyResponse requestApply);

    RequestTimeResponse infoRequest(@NotNull RequestTime requestTime);
}
