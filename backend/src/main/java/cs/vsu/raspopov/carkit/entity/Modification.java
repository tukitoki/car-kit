package cs.vsu.raspopov.carkit.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "modification")
public class Modification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Brand name should not be blank")
    private String name;
    @NotBlank(message = "Engine model should not be blank")
    private String engineModel;
    private Timestamp yearFrom;
    private Timestamp yearTo;
    @ManyToMany
    private List<Detail> details;
    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;
    @OneToMany(mappedBy = "modification")
    private List<DetailMileageChange> detailMileageChange;
}
