package cs.vsu.raspopov.carkit.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;
    @NotNull
    private String phoneNumber;
    @ManyToOne()
    @JoinColumn(name = "auto_repair_shop_id")
    private AutoRepairShop autoRepairShop;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    @ManyToMany
    private List<Detail> details;
    @ManyToMany
    private List<MaintenanceWork> maintenanceWork;
}
