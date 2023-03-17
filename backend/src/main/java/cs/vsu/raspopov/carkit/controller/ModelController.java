package cs.vsu.raspopov.carkit.controller;

import cs.vsu.raspopov.carkit.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;


}
