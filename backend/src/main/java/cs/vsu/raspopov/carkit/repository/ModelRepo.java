package cs.vsu.raspopov.carkit.repository;

import cs.vsu.raspopov.carkit.entity.Brand;
import cs.vsu.raspopov.carkit.entity.Model;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ModelRepo extends CrudRepository<Model, Long> {

    Optional<Model> findByNameAndBrand(@NotNull String name,
                                       @NotNull Brand brand);
}
