package cs.vsu.raspopov.carkit.service;

import cs.vsu.raspopov.carkit.dto.detail.DetailDto;
import cs.vsu.raspopov.carkit.dto.detail.response.DetailAddResponse;

public interface DetailService {

    void saveDetail(DetailDto dto);

    DetailAddResponse showSaveDetail();

    DetailDto getById(Long id);
}