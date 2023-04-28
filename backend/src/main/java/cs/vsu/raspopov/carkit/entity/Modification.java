package cs.vsu.raspopov.carkit.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "modification")
public class Modification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String engineModel;
    @NotNull
    private Integer yearFrom;
    @NotNull
    private Integer yearTo;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "car_detail",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "detail_id"))
    private Set<Detail> details;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;
    @OneToMany(mappedBy = "modification")
    private List<DetailMileageChange> detailMileageChange;
}
