package cs.vsu.raspopov.carkit.controller;

import cs.vsu.raspopov.carkit.dto.request.RequestApplyResponse;
import cs.vsu.raspopov.carkit.dto.request.RequestDetailsMileage;
import cs.vsu.raspopov.carkit.dto.request.RequestTime;
import cs.vsu.raspopov.carkit.dto.request.RequestTimeResponse;
import cs.vsu.raspopov.carkit.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/request")
@RestController
public class RequestController {

    private final RequestService requestService;

    @PostMapping("/apply")
    public void applyRequest(@RequestBody RequestApplyResponse requestApply) {
        requestService.applyRequest(requestApply);
    }

    @GetMapping("/send")
    public void SendRequest(@RequestBody RequestDetailsMileage requestDetailsMileage) {
//        requestService.applyRequest(requestApplyDto);
    }

    @GetMapping("/apply")
    public RequestTimeResponse infoRequest(@RequestParam Long carId,
                                           @RequestParam List<Long> detailIds) {
        return requestService.infoRequest(RequestTime.builder()
                .carId(carId)
                .details(detailIds)
                .build());
    }
}
