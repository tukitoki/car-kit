package cs.vsu.raspopov.carkit.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class CarDto {

    private String brand;
    private String model;
    private ModificationDto modificationDto;
}
