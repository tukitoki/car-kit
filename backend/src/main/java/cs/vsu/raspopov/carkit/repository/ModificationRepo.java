package cs.vsu.raspopov.carkit.repository;

import cs.vsu.raspopov.carkit.entity.Modification;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

public interface ModificationRepo extends CrudRepository<Modification, Long> {

    Optional<Modification> findByNameAndEngineModelAndYearFromAndYearTo(
            String name, String engineModel, Integer yearFrom, Integer yearTo);
}
