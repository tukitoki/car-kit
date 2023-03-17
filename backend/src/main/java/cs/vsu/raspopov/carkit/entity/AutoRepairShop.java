package cs.vsu.raspopov.carkit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "auto_repair_shop")
public class AutoRepairShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String address;

    @OneToMany(mappedBy = "autoRepairShop")
    private List<Contact> contacts;

    @OneToMany(mappedBy = "autoRepairShop")
    private List<Schedule> schedule;

    @OneToMany(mappedBy = "autoRepairShop")
    private List<Request> requests;
}
