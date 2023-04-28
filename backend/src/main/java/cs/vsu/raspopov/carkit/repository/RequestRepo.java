package cs.vsu.raspopov.carkit.repository;

import cs.vsu.raspopov.carkit.entity.AutoRepairShop;
import cs.vsu.raspopov.carkit.entity.Request;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RequestRepo extends CrudRepository<Request, Long> {

    Optional<Request> findByAutoRepairShopAndStartTime(AutoRepairShop autoRepairShop,
                                                       LocalDateTime startTime);

    @Override
    List<Request> findAll();
}
