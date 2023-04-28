package cs.vsu.raspopov.carkit.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDate date;
    @NotNull
    private LocalTime startWorkTime;
    @NotNull
    private LocalTime endWorkTime;
    @ManyToOne()
    @JoinColumn(name = "auto_repair_shop_id")
    private AutoRepairShop autoRepairShop;
}
