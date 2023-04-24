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
public class MaintenanceWorkId implements Serializable {

    private Long carId;
    private Long detailTypeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaintenanceWorkId that = (MaintenanceWorkId) o;
        return Objects.equals(carId, that.carId) && Objects.equals(detailTypeId, that.detailTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, detailTypeId);
    }
}
