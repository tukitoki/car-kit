package cs.vsu.raspopov.carkit.repository;

import cs.vsu.raspopov.carkit.entity.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Integer> {
}
