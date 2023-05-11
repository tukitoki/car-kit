package cs.vsu.raspopov.carkit.mapper;

import cs.vsu.raspopov.carkit.dto.car.ModelDto;
import cs.vsu.raspopov.carkit.dto.car.ModificationDto;
import cs.vsu.raspopov.carkit.entity.Model;
import cs.vsu.raspopov.carkit.entity.Modification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;
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

    public ModelDto toDto(Model model, Modification modification) {
        var modificationDto = modificationMapper.toDto(modification);
        Set<ModificationDto> modifications = new LinkedHashSet<>();
        modifications.add(modificationDto);

        return ModelDto.builder()
                .model(model.getName())
                .modifications(modifications)
                .build();
    }

}
