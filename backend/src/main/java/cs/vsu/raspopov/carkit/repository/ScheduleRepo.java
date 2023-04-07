package cs.vsu.raspopov.carkit.repository;

import cs.vsu.raspopov.carkit.entity.Schedule;
import org.springframework.data.repository.CrudRepository;

public interface ScheduleRepo extends CrudRepository<Schedule, Integer> {
}
