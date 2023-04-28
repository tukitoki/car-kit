package cs.vsu.raspopov.carkit.controller;

import cs.vsu.raspopov.carkit.dto.request.*;
import cs.vsu.raspopov.carkit.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public RequestTimeResponse infoRequest(@RequestBody RequestTime requestTime) {
        return requestService.infoRequest(requestTime);
    }
}
