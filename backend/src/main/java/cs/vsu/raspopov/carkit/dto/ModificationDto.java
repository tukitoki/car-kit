package cs.vsu.raspopov.carkit.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class ModificationDto {

    private String name;
    private String engineModel;
    private Integer yearFrom;
    private Integer yearTo;
}
