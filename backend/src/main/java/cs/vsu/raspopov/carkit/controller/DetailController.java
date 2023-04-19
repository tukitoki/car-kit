package cs.vsu.raspopov.carkit.controller;

import cs.vsu.raspopov.carkit.controller.api.DetailApi;
import cs.vsu.raspopov.carkit.dto.detail.DetailDto;
import cs.vsu.raspopov.carkit.dto.detail.ReplacementDetailDtoResponse;
import cs.vsu.raspopov.carkit.dto.detail.response.DetailAddResponse;
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
    @GetMapping("/add")
    public DetailAddResponse showSaveDetail() {
        return detailService.showSaveDetail();
    }

    @Override
    public void deleteDetail(DetailDto dto) {

    }

    @Override
    public DetailDto getById(Long id) {
        return detailService.getById(id);
    }

    @Override
    public List<ReplacementDetailDtoResponse> getDetailReplacements(Long id) {
        return null;
    }
}
