package cs.vsu.raspopov.carkit.dto;

import lombok.Getter;

@Getter
public class CarDto {

    private Long id;
    private String brand;
    private String model;
    private ModificationDto modificationDto;
}
