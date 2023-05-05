package cs.vsu.raspopov.carkit.mapper;

import cs.vsu.raspopov.carkit.dto.detail.DetailDto;
import cs.vsu.raspopov.carkit.dto.detail.DetailResponse;
import cs.vsu.raspopov.carkit.entity.Detail;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@Component
public class DetailMapper {

    public Detail toEntity(DetailDto detailDto) {
        var localTime = LocalTime.parse(detailDto.getTimeToDelivery());
        return Detail.builder()
                .name(detailDto.getName())
                .description(detailDto.getDescription())
                .price(detailDto.getPrice())
                .producer(detailDto.getProducer())
                .timeToDelivery(Time.valueOf(localTime))
                .build();
    }

    public DetailDto toDto(Detail detail, List<DetailResponse> replacementIds) {
        return DetailDto.builder()
                .id(detail.getId())
                .name(detail.getName())
                .description(detail.getDescription())
                .price(detail.getPrice())
                .producer(detail.getProducer())
                .timeToDelivery(detail.getTimeToDelivery().toString())
                .detailType(detail.getDetailType().getName())
                .dimension(detail.getDimension().getDimensionName())
                .detailResponses(replacementIds)
                .build();

    }
}
