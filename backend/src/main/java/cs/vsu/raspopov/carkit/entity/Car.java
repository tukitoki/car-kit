package cs.vsu.raspopov.carkit.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Car {

    private int id;
    private String brand;
    private String model;
    private String modification;
    private List<Detail> details;
}
