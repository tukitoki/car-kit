package cs.vsu.raspopov.carkit.controller;

import cs.vsu.raspopov.carkit.dto.request.RequestTime;
import cs.vsu.raspopov.carkit.dto.request.RequestTimeResponse;
import cs.vsu.raspopov.carkit.service.RequestService;
import cs.vsu.raspopov.carkit.service.impl.RequestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/request")
@RestController
public class RequestController {

    private final RequestService requestService;

    @PostMapping("/apply")
    public void applyRequest(RequestTime requestTime) {
        requestService.infoRequest(requestTime);
    }

    @GetMapping("/apply")
    public RequestTimeResponse infoRequest(RequestTime requestTime) {
        return requestService.infoRequest(requestTime);
    }
}
