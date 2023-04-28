package cs.vsu.raspopov.carkit.repository;

import cs.vsu.raspopov.carkit.entity.DetailMileageChange;
import cs.vsu.raspopov.carkit.entity.DetailType;
import cs.vsu.raspopov.carkit.entity.Modification;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DetailMileageChangeRepo extends CrudRepository<DetailMileageChange, Long> {

    Optional<DetailMileageChange> findByDetailTypeAndModification(DetailType detailType,
                                                                  Modification modification);
}
