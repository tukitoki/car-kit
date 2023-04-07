package cs.vsu.raspopov.carkit.repository;

import cs.vsu.raspopov.carkit.entity.AutoRepairShop;
import cs.vsu.raspopov.carkit.entity.Request;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface RequestRepo extends CrudRepository<Request, Integer> {

    Optional<Request> findByAutoRepairShopAndDate(AutoRepairShop autoRepairShop, LocalDate date);
}
