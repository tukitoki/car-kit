package cs.vsu.raspopov.carkit.repository;

import cs.vsu.raspopov.carkit.entity.DetailReplacement;
import cs.vsu.raspopov.carkit.entity.DetailReplacementId;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DetailReplacementRepo extends CrudRepository<DetailReplacement, DetailReplacementId> {

    Optional<DetailReplacement> findById_ReplacementDetailId(Long id);
    Optional<DetailReplacement> findById_DetailId(Long id);
}
