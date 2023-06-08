package cs.vsu.raspopov.carkit.service.impl;


import cs.vsu.raspopov.carkit.dto.detail.*;
import cs.vsu.raspopov.carkit.entity.*;
import cs.vsu.raspopov.carkit.entity.enums.DetailEnum;
import cs.vsu.raspopov.carkit.mapper.DetailMapper;
import cs.vsu.raspopov.carkit.repository.*;
import cs.vsu.raspopov.carkit.service.DetailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@RequiredArgsConstructor
@Service
public class DetailServiceImpl implements DetailService {

    private final DetailMapper detailMapper;
    private final DetailRepo detailRepo;
    private final DimensionRepo dimensionRepo;
    private final DetailReplacementRepo detailReplacementRepo;
    private final DetailTypeRepo detailTypeRepo;
    private final ModificationRepo modificationRepo;

    @Override
    @Transactional
    public void saveDetail(DetailDto dto) {
        Detail detail = detailMapper.toEntity(dto);

        var detailType = detailTypeRepo.findByDisplayName(dto.getDetailType())
                .orElseThrow(() -> new NoSuchElementException("NON"));
        detail.setDetailType(detailType);

        var dimension = dimensionRepo.findByDimensionName(dto.getDimension())
                .orElseGet(() -> dimensionRepo.save(Dimension.builder()
                        .dimensionName(dto.getDimension())
                        .build()));
        detail.setDimension(dimension);

        detail = detailRepo.save(detail);

        for (var detailResponse : dto.getDetailResponses()) {
            getDetailById(detailResponse.getId());
            var detailReplacementId = new DetailReplacementId(detail.getId(), detailResponse.getId());
            detailReplacementRepo.save(new DetailReplacement(detailReplacementId));
        }
    }

    @Override
    @Transactional
    public void updateDetail(DetailDto dto, Long id) {
        Detail detail = getDetailById(id);
        Detail newDetail = detailMapper.toEntity(dto);
        detailReplacementRepo.findAllById_DetailId(id).forEach(detailReplacement -> {

        });

        BeanUtils.copyProperties(newDetail, detail, "id");

    }

    @Override
    public List<DetailDto> getAllDetails() {
        List<DetailDto> detailDtos = new LinkedList<>();
        detailRepo.findAll().forEach(detail -> {
            detailDtos.add(detailMapper.toDto(detail, null));
        });

        return detailDtos;
    }

    @Override
    public DetailAddResponse showSaveDetail() {
        List<String> types = new ArrayList<>();
        detailTypeRepo.findAll().forEach(detailType -> {
            types.add(detailType.getDisplayName());
        });

        List<String> dimensions = new ArrayList<>();
        dimensionRepo.findAll().forEach(dimension -> {
            dimensions.add(dimension.getDimensionName());
        });

        List<DetailDto> detailResponses = new ArrayList<>();
        detailRepo.findAll().forEach(detail -> {
            detailResponses.add(detailMapper.toDto(detail, null));
        });
        return DetailAddResponse.builder()
                .details(detailResponses)
                .detailTypes(types)
                .dimensions(dimensions)
                .build();
    }

    @Override
    public DetailDto getById(Long id) {
        var detail = getDetailById(id);
        var listReplacementsIds = detailReplacementRepo.findAllById_DetailId(id)
                .stream()
                .map(detailReplacement -> {
                    var replacement = getDetailById(detailReplacement.getId().getReplacementDetailId());
                    DetailResponse detailResponse = DetailResponse.builder()
                            .name(replacement.getName())
                            .dimension(replacement.getDimension().getDimensionName())
                            .count(replacement.getCount())
                            .id(replacement.getId())
                            .build();
                    return detailResponse;
                })
                .toList();

        return detailMapper.toDto(detail, listReplacementsIds);
    }

    @Override
    public List<DetailMileageDto> getDetailsByMileage(DetailMileageRequest detailMileageRequest) {
        var modification = modificationRepo.findById(detailMileageRequest.getCarId())
                .orElseThrow();
        List<DetailMileageDto> detailMileageDtos = new ArrayList<>();

        for (var detailType : DetailEnum.values()) {
            var list = modification.getDetailMileageChange()
                    .stream()
                    .sorted()
                    .filter(detailMileageChange -> detailMileageChange.getDetailType().getName() == detailType)
                    .toList();

            if (list.isEmpty()) {
                continue;
            }

            DetailMileageChange needableDetail = null;
            int closestMileage = Integer.MIN_VALUE;

            for (var det : list) {
                if (det.getMileage() <= detailMileageRequest.getMileage() && det.getMileage() >= closestMileage
                        && det.getCount().compareTo(BigDecimal.ONE) >= 0) {
                    closestMileage = det.getMileage();
                    needableDetail = det;
                } else if (det.getMileage() <= detailMileageRequest.getMileage()) {
                    closestMileage = Integer.MIN_VALUE;
                    needableDetail = det;
                }
            }

            if (needableDetail == null) {
                needableDetail = list.get(0);
            }

            boolean isNeed = closestMileage != Integer.MIN_VALUE;

            List<DetailDto> detailsToChange = new ArrayList<>();
            List<DetailDto> otherDetails = new ArrayList<>();
            List<Long> detailIds = new ArrayList<>();
            modification.getDetails().forEach(detail -> {
                if (detail.getDetailType().getName().equals(detailType)) {
                    detailIds.add(detail.getId());
                }
            });
            if (detailIds.isEmpty()) {
                continue;
            }
            if (isNeed) {
                detailsToChange.addAll(detailIds.
                        stream()
                        .map(this::getById)
                        .toList());
            } else {
                otherDetails.addAll(detailIds.
                        stream()
                        .map(this::getById)
                        .toList());
            }

            detailMileageDtos.add(DetailMileageDto.builder()
                    .detailType(detailType.toString())
                    .countToChange(needableDetail.getCount())
                    .detailsToChange(detailsToChange)
                    .otherDetails(otherDetails)
                    .build());
        }

//        modification.getDetailMileageChange().forEach(detailMileageChange -> {
//            List<DetailDto> detailsToChange = new ArrayList<>();
//            List<DetailDto> otherDetails = new ArrayList<>();
//            List<Long> detailIds = new ArrayList<>();
//            modification.getDetails().forEach(detail -> {
//                if (detail.getDetailType().equals(detailMileageChange.getDetailType())) {
//                    detailIds.add(detail.getId());
//                }
//            });
//            if (detailMileageRequest.getMileage() >= detailMileageChange.getMileage()) {
//                detailsToChange.addAll(detailIds.
//                        stream()
//                        .map(this::getById)
//                        .toList());
//            } else {
//                otherDetails.addAll(detailIds.
//                        stream()
//                        .map(this::getById)
//                        .toList());
//            }
//
//            detailMileageDtos.add(DetailMileageDto.builder()
//                    .detailType(detailMileageChange.getDetailType().getDisplayName())
//                    .countToChange(detailMileageChange.getCount())
//                    .detailsToChange(detailsToChange)
//                    .otherDetails(otherDetails)
//                    .build());
//        });

        return detailMileageDtos;
    }

    @Override
    public Detail getDetailById(Long id) {
        return detailRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("NON"));
    }
}
