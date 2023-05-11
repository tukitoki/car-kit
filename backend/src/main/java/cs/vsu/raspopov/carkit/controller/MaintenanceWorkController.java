package cs.vsu.raspopov.carkit.controller;

import cs.vsu.raspopov.carkit.controller.api.MaintenanceWorkApi;
import cs.vsu.raspopov.carkit.dto.maintenance_work.MaintenanceWorkDto;
import cs.vsu.raspopov.carkit.service.MaintenanceWorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/work")
@RestController
public class MaintenanceWorkController implements MaintenanceWorkApi {

    private final MaintenanceWorkService maintenanceWorkService;

    @Override
    @PostMapping("/car/{id}")
    public void addCarWork(@PathVariable Long id,
                           @RequestBody MaintenanceWorkDto dto) {
        maintenanceWorkService.addCarWork(id, dto);
    }
}
