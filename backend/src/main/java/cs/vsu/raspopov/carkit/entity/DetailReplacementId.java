package cs.vsu.raspopov.carkit.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class DetailReplacementId implements Serializable {

    private Long detailId;
    private Long replacementDetailId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailReplacementId that = (DetailReplacementId) o;
        return Objects.equals(detailId, that.detailId) && Objects.equals(replacementDetailId, that.replacementDetailId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(detailId, replacementDetailId);
    }
}
