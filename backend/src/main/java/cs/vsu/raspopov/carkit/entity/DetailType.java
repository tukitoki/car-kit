package cs.vsu.raspopov.carkit.entity;

import cs.vsu.raspopov.carkit.entity.enums.DetailEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "detail_types")
@Entity
public class DetailType {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    @ColumnTransformer(read = "UPPER(name)", write = "LOWER(?)")
    @Enumerated(EnumType.STRING)
    private DetailEnum name;

    public DetailType(DetailEnum name) {
        this.name = name;
    }

    public String getName() {
        return name.toString();
    }
}
