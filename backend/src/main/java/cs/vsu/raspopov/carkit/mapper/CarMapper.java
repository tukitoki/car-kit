package cs.vsu.raspopov.carkit.mapper;

import cs.vsu.raspopov.carkit.entity.Brand;
import cs.vsu.raspopov.carkit.entity.Car;
import cs.vsu.raspopov.carkit.entity.Model;
import cs.vsu.raspopov.carkit.entity.Modification;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    public Car toEntity(Brand brand, Model model, Modification modification) {
        return Car.builder()
                .brand(brand)
                .model(model)
                .modification(modification)
                .build();
    }
}
