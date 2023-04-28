package cs.vsu.raspopov.carkit.mapper;

import cs.vsu.raspopov.carkit.dto.car.ModificationDto;
import cs.vsu.raspopov.carkit.entity.Modification;
import org.springframework.stereotype.Component;

@Component
public class ModificationMapper {

    public ModificationDto toDto(Modification modification) {
        return ModificationDto.builder()
                .name(modification.getName())
                .engineModel(modification.getEngineModel())
                .yearFrom(modification.getYearFrom())
                .yearTo(modification.getYearTo())
                .build();
    }
}
