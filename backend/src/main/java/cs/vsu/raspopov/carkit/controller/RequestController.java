package cs.vsu.raspopov.carkit.controller;

import cs.vsu.raspopov.carkit.dto.RequestDto;
import cs.vsu.raspopov.carkit.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @PostMapping("request/apply")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RequestDto> applyRequest(@RequestBody RequestDto requestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(requestService.applyRequest(requestDto));
    }
}
