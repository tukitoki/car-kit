package cs.vsu.raspopov.carkit.service;

import cs.vsu.raspopov.carkit.dto.CarDto;
import cs.vsu.raspopov.carkit.dto.car.CarAddDetailsRequest;
import cs.vsu.raspopov.carkit.dto.car.CarAddDetailsResponse;
import cs.vsu.raspopov.carkit.dto.car.CarDtoResponse;
import cs.vsu.raspopov.carkit.dto.car.response.CarAddResponse;

public interface CarService {

    void saveCar(CarDto dto);

    CarDtoResponse getCarById(Long id);

    CarAddResponse showSaveCar();

    void addDetailsToCar(CarAddDetailsRequest dto, Long id);

    CarAddDetailsResponse showAddDetailsToCar(Long id);
}
