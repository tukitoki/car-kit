package cs.vsu.raspopov.carkit.repository;

import cs.vsu.raspopov.carkit.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(@NotNull String username);

    Optional<User> findByEmail(@NotNull String email);

    @NonNull
    List<User> findAll();
}
