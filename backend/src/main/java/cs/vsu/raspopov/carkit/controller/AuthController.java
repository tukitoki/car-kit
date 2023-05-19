package cs.vsu.raspopov.carkit.controller;

import cs.vsu.raspopov.carkit.controller.api.AuthApi;
import cs.vsu.raspopov.carkit.dto.user.JwtDto;
import cs.vsu.raspopov.carkit.dto.user.UserLoginDto;
import cs.vsu.raspopov.carkit.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController implements AuthApi {

    private final AuthService authService;

    @Override
    @PostMapping("/login")
    public JwtDto loginUser(@RequestBody UserLoginDto userLoginDto) {
        return authService.loginUser(userLoginDto);
    }

    @Override
    @PostMapping("/refresh")
    public JwtDto refreshToken(@RequestBody String refreshToken) {
        return authService.refreshToken(refreshToken);
    }
}
