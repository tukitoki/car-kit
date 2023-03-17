package cs.vsu.raspopov.carkit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate dayOfWeek;

    private Timestamp startWorkTime;

    private Timestamp endWorkTime;

    @ManyToOne()
    @JoinColumn(name = "auto_repair_shop_id")
    private AutoRepairShop autoRepairShop;
}
