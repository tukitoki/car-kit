package cs.vsu.raspopov.carkit.repository;

import cs.vsu.raspopov.carkit.entity.Model;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ModelRepo extends CrudRepository<Model, Long> {

    Optional<Model> findByName(String name);
}
