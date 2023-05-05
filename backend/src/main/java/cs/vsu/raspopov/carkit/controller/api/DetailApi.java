package cs.vsu.raspopov.carkit.controller.api;

import cs.vsu.raspopov.carkit.dto.detail.DetailAddResponse;
import cs.vsu.raspopov.carkit.dto.detail.DetailDto;
import cs.vsu.raspopov.carkit.dto.detail.DetailMileageDto;

public interface DetailApi {

    void saveDetail(DetailDto dto);

    DetailAddResponse showSaveDetail();

    DetailMileageDto getDetailsByMileage(Integer mileage, Long carId);

    DetailDto getById(Long id);
}
