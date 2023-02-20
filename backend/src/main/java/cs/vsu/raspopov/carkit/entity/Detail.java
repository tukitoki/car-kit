package cs.vsu.raspopov.carkit.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "detail")
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int article;
    private String name;
    private String producer;
    private int price;
    @ManyToMany
    @JoinTable(
            name = "car_modification_details",
            joinColumns = @JoinColumn(name = "car_modification_id"),
            inverseJoinColumns = @JoinColumn(name = "detail_id")
    )
    private List<CarModification> carModifications;
}
