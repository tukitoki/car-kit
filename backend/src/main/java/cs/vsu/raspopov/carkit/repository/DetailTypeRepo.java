package cs.vsu.raspopov.carkit.repository;

import cs.vsu.raspopov.carkit.entity.DetailType;
import cs.vsu.raspopov.carkit.entity.enums.DetailEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DetailTypeRepo extends CrudRepository<DetailType, Long> {

    Optional<DetailType> findByName(DetailEnum name);

    @Query(value = "SELECT COUNT(*) from  DetailType")
    int getAllTypes();
}
