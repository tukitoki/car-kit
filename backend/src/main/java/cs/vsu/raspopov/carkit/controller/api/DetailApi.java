package cs.vsu.raspopov.carkit.controller.api;

import cs.vsu.raspopov.carkit.dto.detail.DetailDtoRequest;
import cs.vsu.raspopov.carkit.dto.detail.DetailDtoResponse;
import cs.vsu.raspopov.carkit.dto.detail.ReplacementDetailDtoResponse;

import java.util.List;

public interface DetailApi {

    void saveDetail(DetailDtoRequest dto);

    void deleteDetail(DetailDtoRequest dto);

    DetailDtoResponse getDtoById(Integer id);

    List<ReplacementDetailDtoResponse> getDetailReplacement(Integer id);
}
