package cs.vsu.raspopov.carkit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auto_repair_shop")
@Entity
public class AutoRepairShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String address;

    @OneToMany(mappedBy = "autoRepairShop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contact> contacts;

    @OneToMany(mappedBy = "autoRepairShop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> schedule;

    @OneToMany(mappedBy = "autoRepairShop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Request> requests;
}


