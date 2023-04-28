package cs.vsu.raspopov.carkit.mapper;

import cs.vsu.raspopov.carkit.dto.car.BrandDto;
import cs.vsu.raspopov.carkit.entity.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
}
