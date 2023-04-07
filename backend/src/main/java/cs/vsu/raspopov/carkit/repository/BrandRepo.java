package cs.vsu.raspopov.carkit.repository;

import cs.vsu.raspopov.carkit.entity.Brand;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BrandRepo extends CrudRepository<Brand, Integer> {

    Optional<Brand> findByName(String name);
}
