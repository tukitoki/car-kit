package cs.vsu.raspopov.carkit.controller.api;

import cs.vsu.raspopov.carkit.dto.detail.DetailDto;
import cs.vsu.raspopov.carkit.dto.detail.ReplacementDetailDtoResponse;
import cs.vsu.raspopov.carkit.dto.detail.response.DetailAddResponse;

import java.util.List;

public interface DetailApi {

    void saveDetail(DetailDto dto);

    DetailAddResponse showSaveDetail();

    void deleteDetail(DetailDto dto);

    DetailDto getById(Long id);

    List<ReplacementDetailDtoResponse> getDetailReplacements(Long id);
}
