package cs.vsu.raspopov.carkit.mapper;

import cs.vsu.raspopov.carkit.dto.detail.DetailDto;
import cs.vsu.raspopov.carkit.entity.Detail;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class DetailMapper {

    public Detail toEntity(DetailDto detailDto) {
        return Detail.builder()
                .name(detailDto.getName())
                .description(detailDto.getDescription())
                .price(detailDto.getPrice())
                .producer(detailDto.getProducer())
                .timeToDelivery(Timestamp.valueOf(LocalDateTime.now().plusDays(5)))
                .build();
    }

    public DetailDto toDto(Detail detail) {
        return DetailDto.builder()
                .id(detail.getId())
                .name(detail.getName())
                .description(detail.getDescription())
                .price(detail.getPrice())
                .producer(detail.getProducer())
                .timeToDelivery(detail.getTimeToDelivery())
                .detailType(detail.getDetailType().getName())
                .dimension(detail.getDimension().getDimensionName())
                .build();

    }
}
