package cs.vsu.raspopov.carkit.mapper;

import cs.vsu.raspopov.carkit.dto.car.BrandDto;
import cs.vsu.raspopov.carkit.dto.car.ModelDto;
import cs.vsu.raspopov.carkit.entity.Brand;
import cs.vsu.raspopov.carkit.entity.Model;
import cs.vsu.raspopov.carkit.entity.Modification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class BrandMapper {

    private final ModelMapper modelMapper;

    public BrandDto toDto(Brand brand) {
        var modelDtos = brand.getModels()
                .stream()
                .map(modelMapper::toDto)
                .collect(Collectors.toSet());

        return BrandDto.builder()
                .brand(brand.getName())
                .models(modelDtos)
                .build();
    }

    public BrandDto toDto(Brand brand, Model model, Modification modification) {
        var modelDto = modelMapper.toDto(model, modification);
        Set<ModelDto> modelDtos = new LinkedHashSet<>();
        modelDtos.add(modelDto);

        return BrandDto.builder()
                .brand(brand.getName())
                .models(modelDtos)
                .build();
    }
}
