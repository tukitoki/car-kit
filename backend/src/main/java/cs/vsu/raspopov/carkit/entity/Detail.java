package cs.vsu.raspopov.carkit.entity;

import cs.vsu.raspopov.carkit.entity.enums.DetailType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "detail")
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private String producer;
    private Timestamp timeToChange;
    private Timestamp timeToDelivery;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "dimension_id")
    private Dimension dimension;
    @ManyToMany
    private List<Replacement> replacements;
    @Enumerated(EnumType.STRING)
    private DetailType detailType;
}
