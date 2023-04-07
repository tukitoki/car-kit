package cs.vsu.raspopov.carkit.controller.api;

import cs.vsu.raspopov.carkit.dto.car.CarDtoRequest;
import cs.vsu.raspopov.carkit.dto.car.CarDtoResponse;

public interface CarApi {

    void saveCar(CarDtoRequest dto);

    CarDtoResponse getCarById(Long id);


}
