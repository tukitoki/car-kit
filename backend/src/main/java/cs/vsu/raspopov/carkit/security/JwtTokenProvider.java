package cs.vsu.raspopov.carkit.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import cs.vsu.raspopov.carkit.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.access-validity}")
    private long accessValidity;
    @Value("${jwt.refresh-validity}")
    private long refreshValidity;

    public String generateAccessToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + accessValidity))
                .withClaim("id", user.getId())
                .withClaim("authorities", user.getRole().getAuthorities()
                        .stream()
                        .map(SimpleGrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .sign(algorithm);
    }

    public String generateRefreshToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshValidity))
                .sign(algorithm);
    }

    public UsernamePasswordAuthenticationToken getAuthTokenFromJwt(String jwtToken) {
        DecodedJWT decodedJWT = decodeJwt(jwtToken);
        String username = decodedJWT.getSubject();
        List<String> list = decodedJWT.getClaim("authorities").asList(String.class);
        List<SimpleGrantedAuthority> authorities = list
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }

    public String getUsernameFromJwt(String jwtToken) {
        DecodedJWT decodedJWT = decodeJwt(jwtToken);
        return decodedJWT.getSubject();
    }

    private DecodedJWT decodeJwt(String jwtToken) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(jwtToken);
    }
}
