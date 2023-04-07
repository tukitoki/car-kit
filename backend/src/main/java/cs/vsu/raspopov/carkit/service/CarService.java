package cs.vsu.raspopov.carkit.service;

import cs.vsu.raspopov.carkit.dto.car.CarDtoRequest;
import cs.vsu.raspopov.carkit.dto.car.CarDtoResponse;

public interface CarService {

    void saveCar(CarDtoRequest dto);

    CarDtoResponse getCarById(Long id);
}
