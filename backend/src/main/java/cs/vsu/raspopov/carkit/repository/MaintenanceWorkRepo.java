package cs.vsu.raspopov.carkit.repository;

import cs.vsu.raspopov.carkit.entity.MaintenanceWork;
import org.springframework.data.repository.CrudRepository;

public interface MaintenanceWorkRepo extends CrudRepository<MaintenanceWork, Long> {
}
