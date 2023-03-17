package cs.vsu.raspopov.carkit.service;

import cs.vsu.raspopov.carkit.dto.DetailDto;
import cs.vsu.raspopov.carkit.entity.Detail;
import cs.vsu.raspopov.carkit.entity.Dimension;
import cs.vsu.raspopov.carkit.repository.DetailRepo;
import cs.vsu.raspopov.carkit.repository.DimensionRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetailService {

    private final DetailRepo detailRepo;
    private final DimensionRepo dimensionRepo;
    private final ModelMapper mapper = new ModelMapper();

    public DetailDto saveDetail(DetailDto detailDto) {
        Detail detail = mapper.map(detailDto, Detail.class);
        Optional<Dimension> dimension = dimensionRepo
                .findByDimensionName(detail.getDimension().getDimensionName());
        if (dimension.isPresent()) {
            detail.setDimension(dimension.get());
        }
        detail = detailRepo.save(detail);
        return mapper.map(detail, DetailDto.class);
    }
}
