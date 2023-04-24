package cs.vsu.raspopov.carkit.controller;

import cs.vsu.raspopov.carkit.controller.api.CarApi;
import cs.vsu.raspopov.carkit.dto.CarDto;
import cs.vsu.raspopov.carkit.dto.car.CarAddDetailsRequest;
import cs.vsu.raspopov.carkit.dto.car.CarAddDetailsResponse;
import cs.vsu.raspopov.carkit.dto.car.CarDtoResponse;
import cs.vsu.raspopov.carkit.dto.car.response.CarAddResponse;
import cs.vsu.raspopov.carkit.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping
    public CarAddResponse showSaveCar() {
        return carService.showSaveCar();
    }

    @Override
    @GetMapping("/{id}")
    public CarDtoResponse getCarById(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @PostMapping("/{id}/add-details")
    public void addDetailsToCar(@RequestBody CarAddDetailsRequest dto,
                                @PathVariable Long id) {
        carService.addDetailsToCar(dto, id);
    }

    @GetMapping("/{id}/add-details")
    public CarAddDetailsResponse showAddDetailsToCar(@PathVariable Long id) {
        return carService.showAddDetailsToCar(id);
    }
}
