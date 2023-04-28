package cs.vsu.raspopov.carkit.controller.api;

import cs.vsu.raspopov.carkit.dto.detail.*;

import java.util.List;

public interface DetailApi {

    void saveDetail(DetailDto dto);

    DetailAddResponse showSaveDetail();

    List<DetailMileageDto> getDetailsByMileage(DetailMileageRequest detailMileageRequest);

    DetailDto getById(Long id);
}
