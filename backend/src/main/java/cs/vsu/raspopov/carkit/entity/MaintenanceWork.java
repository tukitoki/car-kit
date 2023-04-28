package cs.vsu.raspopov.carkit.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "maintenance_work")
@Entity
public class MaintenanceWork {

    @NotNull
    @EmbeddedId
    private MaintenanceWorkId id;
    @NotNull
    @ManyToOne
    private Dimension dimension;
    @NotNull
    private LocalTime timeToChange;
}
