package cs.vsu.raspopov.carkit.controller.api;

import cs.vsu.raspopov.carkit.dto.car.BrandDto;
import cs.vsu.raspopov.carkit.dto.car.CarDto;
import cs.vsu.raspopov.carkit.dto.page.PageModel;

public interface CarApi {

    void saveCar(CarDto dto);

    CarDto getCarById(Long id);

    PageModel<BrandDto> getAllCars(int pageNumber, int pageSize, String brand, String model);
}
