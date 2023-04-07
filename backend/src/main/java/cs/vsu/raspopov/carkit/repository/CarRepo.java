package cs.vsu.raspopov.carkit.repository;

import cs.vsu.raspopov.carkit.entity.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepo extends CrudRepository<Car, Long> {
}
