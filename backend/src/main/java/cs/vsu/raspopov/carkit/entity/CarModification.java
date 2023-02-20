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
@Table(name = "car_modification")
public class CarModification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String modification;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    @ManyToMany(mappedBy = "carModifications")
    private List<Detail> details;
}
