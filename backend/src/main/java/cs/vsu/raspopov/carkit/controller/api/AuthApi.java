package cs.vsu.raspopov.carkit.controller.api;

import cs.vsu.raspopov.carkit.dto.user.JwtDto;
import cs.vsu.raspopov.carkit.dto.user.UserLoginDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Auth API", description = "API для входа в систему и refresh JWT-token")
public interface AuthApi {

    @Operation(summary = "Возвращает jwt и refresh token's")
    JwtDto loginUser(
            @Parameter(description = "Логин и пароль пользователя")
            UserLoginDto userLoginDto
    );

    @Operation(summary = "Возвращает обновленные jwt и refresh token's")
    JwtDto refreshToken(
            @Parameter(description = "Refresh токен")
            String refreshToken
    );
}
