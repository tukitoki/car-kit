package cs.vsu.raspopov.carkit.service.impl;

import cs.vsu.raspopov.carkit.dto.user.JwtDto;
import cs.vsu.raspopov.carkit.dto.user.UserLoginDto;
import cs.vsu.raspopov.carkit.entity.User;
import cs.vsu.raspopov.carkit.security.JwtTokenProvider;
import cs.vsu.raspopov.carkit.service.AuthService;
import cs.vsu.raspopov.carkit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtDto loginUser(UserLoginDto userLoginDto) {
        User user = userService.getUserByUsername(userLoginDto.getUsername());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginDto.getUsername(), userLoginDto.getPassword()));

        return new JwtDto(jwtTokenProvider.generateAccessToken(user), jwtTokenProvider.generateRefreshToken(user));
    }

    @Override
    public JwtDto refreshToken(String refreshToken) {
        String username = jwtTokenProvider.getUsernameFromJwt(refreshToken);
        User user = userService.getUserByUsername(username);

        return new JwtDto(jwtTokenProvider.generateAccessToken(user), jwtTokenProvider.generateRefreshToken(user));
    }
}
