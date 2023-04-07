package cs.vsu.raspopov.carkit.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class ModificationDtoRequest {

    private String name;
    private String engineModel;
    private Date yearFrom;
    private Date yearTo;
}
