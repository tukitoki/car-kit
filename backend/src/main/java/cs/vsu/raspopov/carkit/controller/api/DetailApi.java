package cs.vsu.raspopov.carkit.controller.api;

import cs.vsu.raspopov.carkit.dto.detail.DetailAddResponse;
import cs.vsu.raspopov.carkit.dto.detail.DetailDto;
import cs.vsu.raspopov.carkit.dto.detail.DetailMileageDto;

import java.util.List;

public interface DetailApi {

    void saveDetail(DetailDto dto);

    void updateDetail(DetailDto dto, Long id);

    DetailAddResponse showSaveDetail();

    List<DetailMileageDto> getDetailsByMileage(Integer mileage, Long carId);

    DetailDto getById(Long id);
}
