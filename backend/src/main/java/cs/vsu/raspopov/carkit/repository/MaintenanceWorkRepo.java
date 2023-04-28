package cs.vsu.raspopov.carkit.repository;

import cs.vsu.raspopov.carkit.entity.MaintenanceWork;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MaintenanceWorkRepo extends CrudRepository<MaintenanceWork, Long> {

    Optional<MaintenanceWork> findById_CarIdAndId_DetailTypeId(Long carId, Long detailTypeId);
}
