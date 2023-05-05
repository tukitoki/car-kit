package cs.vsu.raspopov.carkit.mapper;

import cs.vsu.raspopov.carkit.dto.car.ModelDto;
import cs.vsu.raspopov.carkit.entity.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ModelMapper {

    private final ModificationMapper modificationMapper;

    public ModelDto toDto(Model model) {
        var modificationDtos = model.getModifications()
                .stream()
                .map(modificationMapper::toDto)
                .collect(Collectors.toSet());
        return ModelDto.builder()
                .model(model.getName())
                .modifications(modificationDtos)
                .build();
    }
}
