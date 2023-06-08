package cs.vsu.raspopov.carkit.controller;

import cs.vsu.raspopov.carkit.dto.page.PageModel;
import cs.vsu.raspopov.carkit.dto.page.SortDirection;
import cs.vsu.raspopov.carkit.dto.request.*;
import cs.vsu.raspopov.carkit.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/api/request")
@RestController
public class RequestController {

    private final RequestService requestService;

    @GetMapping("/all")
    public PageModel<RequestDto> getAllRequests(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "ASC") SortDirection order) {
        return requestService.getAllCars(pageNumber,  pageSize, order);
    }

    @PostMapping("/apply")
    public void applyRequest(@RequestBody RequestApplyResponse requestApply) {
        requestService.applyRequest(requestApply);
    }

    @GetMapping("/apply")
    public RequestTimeResponse infoRequest(@RequestParam Long carId,
                                           @RequestParam List<Long> detailIds) {
        Map<Long, Integer> map = new HashMap<>();
        detailIds.forEach(detailId -> {
            map.put(detailId, 0);
        });

        return requestService.infoRequest(RequestTime.builder()
                .carId(carId)
                .details(map)
                .build());
    }
}
