package cs.vsu.raspopov.carkit.controller;

import cs.vsu.raspopov.carkit.dto.DetailDto;
import cs.vsu.raspopov.carkit.service.DetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class DetailController {

    private final DetailService detailService;

    @PostMapping("detail/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DetailDto> saveDetail(@RequestBody DetailDto detailDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(detailService.saveDetail(detailDto));
    }
}
