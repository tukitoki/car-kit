package cs.vsu.raspopov.carkit.controller;

import cs.vsu.raspopov.carkit.dto.BrandDto;
import cs.vsu.raspopov.carkit.service.BrandService;
import cs.vsu.raspopov.carkit.service.DetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BrandDto> saveBrand(@RequestBody BrandDto brandDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(brandDto);
    }
}
