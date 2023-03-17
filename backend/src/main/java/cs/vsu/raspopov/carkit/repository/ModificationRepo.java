package cs.vsu.raspopov.carkit.repository;

import cs.vsu.raspopov.carkit.entity.Modification;
import org.springframework.data.repository.CrudRepository;

public interface ModificationRepo extends CrudRepository<Modification, Integer> {
}
