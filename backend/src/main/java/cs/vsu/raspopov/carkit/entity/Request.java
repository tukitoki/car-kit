package cs.vsu.raspopov.carkit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Timestamp startTime;

    private Timestamp endTime;

    @ManyToOne()
    @JoinColumn(name = "auto_repair_shop_id")
    private AutoRepairShop autoRepairShop;
    @ManyToMany
    private List<Detail> details;
    @ManyToMany
    private List<MaintenanceWork> maintenanceWork;
}
