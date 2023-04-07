package cs.vsu.raspopov.carkit.controller;

import cs.vsu.raspopov.carkit.controller.api.DetailApi;
import cs.vsu.raspopov.carkit.dto.detail.DetailDtoRequest;
import cs.vsu.raspopov.carkit.dto.detail.DetailDtoResponse;
import cs.vsu.raspopov.carkit.dto.detail.ReplacementDetailDtoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/detail")
@RestController
public class DetailController implements DetailApi {

    @Override
    public void saveDetail(DetailDtoRequest dto) {

    }

    @Override
    public void deleteDetail(DetailDtoRequest dto) {

    }

    @Override
    public DetailDtoResponse getDtoById(Integer id) {
        return null;
    }

    @Override
    public List<ReplacementDetailDtoResponse> getDetailReplacement(Integer id) {
        return null;
    }
}
