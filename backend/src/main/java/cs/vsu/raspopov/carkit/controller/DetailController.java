package cs.vsu.raspopov.carkit.controller;

import cs.vsu.raspopov.carkit.controller.api.DetailApi;
import cs.vsu.raspopov.carkit.dto.detail.DetailAddResponse;
import cs.vsu.raspopov.carkit.dto.detail.DetailDto;
import cs.vsu.raspopov.carkit.dto.detail.DetailMileageDto;
import cs.vsu.raspopov.carkit.dto.detail.DetailMileageRequest;
import cs.vsu.raspopov.carkit.service.DetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/detail")
@RestController
public class DetailController implements DetailApi {

    private final DetailService detailService;

    @Override
    @PostMapping("/add")
    public void saveDetail(@RequestBody DetailDto dto) {
        detailService.saveDetail(dto);
    }

    @Override
    @PutMapping("/{id}")
    public void updateDetail(@RequestBody DetailDto dto,
                             @PathVariable Long id) {

    }

    @GetMapping("/all")
    public List<DetailDto> getAllDetails() {
        return detailService.getAllDetails();
    }

    @Override
    @GetMapping("/add")
    public DetailAddResponse showSaveDetail() {
        return detailService.showSaveDetail();
    }

    @Override
    @GetMapping("/mileage-details")
    public DetailMileageDto getDetailsByMileage(@RequestParam Integer mileage,
                                                @RequestParam Long carId) {
        return detailService.getDetailsByMileage(new DetailMileageRequest(mileage, carId));
    }

    @Override
    @GetMapping("/{id}")
    public DetailDto getById(@PathVariable Long id) {
        return detailService.getById(id);
    }
}
