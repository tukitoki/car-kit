package cs.vsu.raspopov.carkit.service;

import cs.vsu.raspopov.carkit.dto.BrandDto;
import cs.vsu.raspopov.carkit.entity.Brand;
import cs.vsu.raspopov.carkit.repository.BrandRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepo brandRepo;
    private final ModelMapper mapper = new ModelMapper();

    public BrandDto saveBrand(BrandDto brandDto) {
        Brand brand = mapper.map(brandDto, Brand.class);
        brand = brandRepo.save(brand);
        return mapper.map(brand, BrandDto.class);
    }
}
