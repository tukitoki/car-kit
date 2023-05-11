package cs.vsu.raspopov.carkit.service;

import cs.vsu.raspopov.carkit.dto.car.BrandDto;
import cs.vsu.raspopov.carkit.dto.car.CarAddDetailsRequest;
import cs.vsu.raspopov.carkit.dto.car.CarAddDetailsResponse;
import cs.vsu.raspopov.carkit.dto.car.CarDto;
import cs.vsu.raspopov.carkit.dto.detail.DetailMileageAdd;
import cs.vsu.raspopov.carkit.dto.page.PageModel;
import cs.vsu.raspopov.carkit.entity.Car;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface CarService {

    void saveCar(@NotNull CarDto dto);

    CarDto getCarById(@NotNull Long id);

    PageModel<BrandDto> getAllCars(int pageNumber, int pageSize, String brand, String model);

    Car getCar(@NotNull Long id);

    void addMileageDetails(@NotNull List<DetailMileageAdd> dto, @NotNull Long id);

    void addDetailsToCar(@NotNull CarAddDetailsRequest dto, @NotNull Long id);

    CarAddDetailsResponse showAddDetailsToCar(@NotNull Long id);
}
