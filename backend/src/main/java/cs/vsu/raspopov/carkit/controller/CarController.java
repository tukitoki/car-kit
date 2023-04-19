package cs.vsu.raspopov.carkit.controller;

import cs.vsu.raspopov.carkit.controller.api.CarApi;
import cs.vsu.raspopov.carkit.dto.CarDto;
import cs.vsu.raspopov.carkit.dto.car.CarDtoResponse;
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
    public void showSaveCar() {
        carService.showSaveCar();
    }

    @Override
    @GetMapping("/{id}")
    public CarDtoResponse getCarById(@PathVariable Long id) {
        return carService.getCarById(id);
    }
}
