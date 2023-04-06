package cs.vsu.raspopov.carkit.entity;

import cs.vsu.raspopov.carkit.entity.enums.DetailType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "maintenance_work")
@Entity
public class MaintenanceWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private DetailType detailType;

    @ManyToOne
    private Dimension dimension;

    private Time timeToChange;

    @ManyToOne
    private Car car;
}
