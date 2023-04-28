package cs.vsu.raspopov.carkit.repository;

import cs.vsu.raspopov.carkit.entity.MaintenanceWork;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MaintenanceWorkRepo extends CrudRepository<MaintenanceWork, Long> {

    MaintenanceWork findAllById_CarIdAndId_DetailTypeId(Long carId, Long detailTypeId);
}
