package cs.vsu.raspopov.carkit.controller.api;

import cs.vsu.raspopov.carkit.dto.CarDto;
import cs.vsu.raspopov.carkit.dto.car.CarDtoResponse;

public interface CarApi {

    void saveCar(CarDto dto);

    CarDtoResponse getCarById(Long id);

    void showSaveCar();
}
