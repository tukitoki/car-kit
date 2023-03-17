package cs.vsu.raspopov.carkit.repository;

import cs.vsu.raspopov.carkit.entity.Dimension;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DimensionRepo extends CrudRepository<Dimension, Integer> {

    Optional<Dimension> findByDimensionName(String dimensionName);
}
