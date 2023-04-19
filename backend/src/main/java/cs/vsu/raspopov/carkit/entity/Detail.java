package cs.vsu.raspopov.carkit.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "detail")
public class Detail {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail+id")
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    @Positive
    private BigDecimal price;
    @NotNull
    private String producer;
    @NotNull
    private Timestamp timeToDelivery;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "dimension_id")
    private Dimension dimension;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "detail_id")
    private DetailType detailType;
    @ManyToMany(mappedBy = "details")
    private Set<Modification> modifications;
}
