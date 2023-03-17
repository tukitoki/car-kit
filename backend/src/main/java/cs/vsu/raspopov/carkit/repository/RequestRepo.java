package cs.vsu.raspopov.carkit.repository;

import cs.vsu.raspopov.carkit.entity.Request;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepo extends CrudRepository<Request, Integer> {
}
