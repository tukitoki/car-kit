package cs.vsu.raspopov.carkit.service;

import cs.vsu.raspopov.carkit.dto.user.JwtDto;
import cs.vsu.raspopov.carkit.dto.user.UserLoginDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface AuthService {

    JwtDto loginUser(@NotNull UserLoginDto userLoginDto);

    JwtDto refreshToken(@NotNull @NotBlank String refreshToken);
}
