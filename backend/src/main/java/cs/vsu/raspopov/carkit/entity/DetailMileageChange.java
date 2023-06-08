package cs.vsu.raspopov.carkit.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Comparator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "detail_mileage_change")
public class DetailMileageChange implements Comparable<DetailMileageChange> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Positive
    private Integer mileage;
    @NotNull
    private BigDecimal count;
    @ManyToOne
    @JoinColumn(name = "modification_id")
    private Modification modification;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "dimension_id")
    private Dimension dimension;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "detail_type_id")
    private DetailType detailType;

    @Override
    public int compareTo(DetailMileageChange o1) {
        return Integer.compare(this.mileage, o1.mileage);
    }
}
