package cs.vsu.raspopov.carkit.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Detail {

    private int id;

    private int article;

    private String name;

    private String producer;

    private List<Car> cars;

    private int price;
}
