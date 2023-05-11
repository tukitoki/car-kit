package cs.vsu.raspopov.carkit.repository;

import cs.vsu.raspopov.carkit.entity.Model;
import cs.vsu.raspopov.carkit.entity.Modification;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ModificationRepo extends CrudRepository<Modification, Long> {

    Optional<Modification> findByNameAndEngineModelAndYearFromAndYearToAndModel(
            @NotNull String name, @NotNull String engineModel,
            @NotNull Integer yearFrom, @NotNull Integer yearTo, @NotNull Model model);
}
