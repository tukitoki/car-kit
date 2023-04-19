package cs.vsu.raspopov.carkit.service.impl;


import cs.vsu.raspopov.carkit.dto.detail.DetailDto;
import cs.vsu.raspopov.carkit.dto.detail.response.DetailAddResponse;
import cs.vsu.raspopov.carkit.dto.detail.response.DetailResponse;
import cs.vsu.raspopov.carkit.entity.*;
import cs.vsu.raspopov.carkit.entity.enums.DetailEnum;
import cs.vsu.raspopov.carkit.mapper.DetailMapper;
import cs.vsu.raspopov.carkit.repository.DetailReplacementRepo;
import cs.vsu.raspopov.carkit.repository.DetailRepo;
import cs.vsu.raspopov.carkit.repository.DetailTypeRepo;
import cs.vsu.raspopov.carkit.repository.DimensionRepo;
import cs.vsu.raspopov.carkit.service.DetailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class DetailServiceImpl implements DetailService {

    private final DetailMapper detailMapper;
    private final DetailRepo detailRepo;
    private final DimensionRepo dimensionRepo;
    private final DetailReplacementRepo detailReplacementRepo;
    private final DetailTypeRepo detailTypeRepo;

    @Override
    @Transactional
    public void saveDetail(DetailDto dto) {
        Detail detail = detailMapper.toEntity(dto);
        var detailType = detailTypeRepo.findByName(DetailEnum.valueOf(dto.getDetailType()))
                .orElseThrow(() -> new NoSuchElementException("NON"));
        detail.setDetailType(detailType);
        var dimension = dimensionRepo.findByDimensionName(dto.getDimension())
                .orElseGet(() -> dimensionRepo.save(Dimension.builder()
                        .dimensionName(dto.getDimension())
                        .build()));
        detail.setDimension(dimension);
        detail = detailRepo.save(detail);
        for (var replacementId : dto.getReplacementIds()) {
            var detailReplacementId = new DetailReplacementId(detail.getId(), replacementId);
            detailReplacementRepo.save(new DetailReplacement(detailReplacementId));
        }
    }

    @Override
    public DetailAddResponse showSaveDetail() {
        ArrayList<String> types = new ArrayList<>();
        detailTypeRepo.findAll().forEach(detailType -> {
            types.add(detailType.getName());
        });

        ArrayList<String> dimensions = new ArrayList<>();
        dimensionRepo.findAll().forEach(dimension -> {
            dimensions.add(dimension.getDimensionName());
        });

        ArrayList<DetailResponse> detailResponses = new ArrayList<>();
        detailRepo.findAll().forEach(detail -> {
            detailResponses.add(DetailResponse.builder()
                    .id(detail.getId())
                    .name(detail.getName())
                    .build());
        });
        return DetailAddResponse.builder()
                .details(detailResponses)
                .detailTypes(types)
                .dimensions(dimensions)
                .build();
    }

    public DetailDto getById(Long id) {
        var detail = detailRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("NON"));

        return detailMapper.toDto(detail);
    }


    private void addDetailTypesToDB() {
        var types = Arrays.stream(DetailEnum.values()).toList();
        types.forEach(detailEnum -> {
            detailTypeRepo.save(new DetailType(detailEnum));
        });
    }
}
