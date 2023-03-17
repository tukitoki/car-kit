package cs.vsu.raspopov.carkit.entity;

import cs.vsu.raspopov.carkit.entity.enums.DetailType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "detail_mileage_change")
public class DetailMileageChange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private DetailType detailType;

    @ManyToOne
    @JoinColumn(name = "modification_id")
    private Modification modification;
    private Integer mileage;
}
