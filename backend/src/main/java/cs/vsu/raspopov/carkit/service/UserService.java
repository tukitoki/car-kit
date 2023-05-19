package cs.vsu.raspopov.carkit.service;

import cs.vsu.raspopov.carkit.dto.user.UserDto;
import cs.vsu.raspopov.carkit.entity.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UserService {

    UserDto findUserById(@NotNull Long id);

    void createUser(@NotNull @Valid UserDto userDto);

    void updateUser(@NotNull @Valid UserDto userDto,
                    @NotNull Long id);

    void deleteUser(@NotNull Long id);

    User getUserById(@NotNull Long id);

    User getUserByUsername(@NotNull String username);
}
