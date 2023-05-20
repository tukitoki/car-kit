package cs.vsu.raspopov.carkit.mapper;

import cs.vsu.raspopov.carkit.dto.car.CarDto;
import cs.vsu.raspopov.carkit.entity.Brand;
import cs.vsu.raspopov.carkit.entity.Car;
import cs.vsu.raspopov.carkit.entity.Model;
import cs.vsu.raspopov.carkit.entity.Modification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CarMapper {

    private final ModificationMapper modificationMapper;

    public Car toEntity(Brand brand, Model model, Modification modification) {
        return Car.builder()
                .brand(brand)
                .model(model)
                .modification(modification)
                .build();
    }

    public CarDto toDto(Car car) {
        return CarDto.builder()
                .id(car.getId())
                .brand(car.getBrand().getName())
                .model(car.getModel().getName())
                .modificationDto(modificationMapper.toDto(car.getModification()))
                .build();
    }
}
