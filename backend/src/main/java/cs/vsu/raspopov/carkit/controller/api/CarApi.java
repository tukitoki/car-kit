package cs.vsu.raspopov.carkit.controller.api;

import cs.vsu.raspopov.carkit.dto.car.CarDto;
import cs.vsu.raspopov.carkit.dto.car.BrandDto;

import java.util.List;

public interface CarApi {

    void saveCar(CarDto dto);

    CarDto getCarById(Long id);

    List<BrandDto> showSaveCar();

    List<BrandDto> getAllCars();
}
