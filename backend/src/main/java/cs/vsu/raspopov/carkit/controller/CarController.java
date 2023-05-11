package cs.vsu.raspopov.carkit.controller;

import cs.vsu.raspopov.carkit.controller.api.CarApi;
import cs.vsu.raspopov.carkit.dto.car.BrandDto;
import cs.vsu.raspopov.carkit.dto.car.CarAddDetailsRequest;
import cs.vsu.raspopov.carkit.dto.car.CarAddDetailsResponse;
import cs.vsu.raspopov.carkit.dto.car.CarDto;
import cs.vsu.raspopov.carkit.dto.detail.DetailMileageAdd;
import cs.vsu.raspopov.carkit.dto.page.PageModel;
import cs.vsu.raspopov.carkit.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/car")
@RestController
public class CarController implements CarApi {

    private final CarService carService;

    @Override
    @PostMapping
    public void saveCar(@RequestBody CarDto dto) {
        carService.saveCar(dto);
    }

    @Override
    @GetMapping("/all")
    public PageModel<BrandDto> getAllCars(@RequestParam(defaultValue = "1") int pageNumber,
                                          @RequestParam(defaultValue = "10") int pageSize,
                                          @RequestParam(required = false) String brand,
                                          @RequestParam(required = false) String model) {
        return carService.getAllCars(pageNumber, pageSize, brand, model);
    }

    @Override
    @GetMapping("/{id}")
    public CarDto getCarById(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @PostMapping("/{id}/add-details")
    public void addDetailsToCar(@RequestBody CarAddDetailsRequest dto,
                                @PathVariable Long id) {
        carService.addDetailsToCar(dto, id);
    }

    @PostMapping("/{id}/add-mileage")
    public void addMileageDetails(@RequestBody List<DetailMileageAdd> dto,
                                  @PathVariable Long id) {
        carService.addMileageDetails(dto, id);
    }

    @GetMapping("/{id}/add-details")
    public CarAddDetailsResponse showAddDetailsToCar(@PathVariable Long id) {
        return carService.showAddDetailsToCar(id);
    }
}
