package cs.vsu.raspopov.carkit.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static cs.vsu.raspopov.carkit.entity.enums.Authority.*;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    USER(Set.of()),
    ADMIN(Set.of(CARS_EDIT, MILEAGE_ADD, MAINT_WORK))
    ;

    private final Set<Authority> authorities;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.name()))
                .collect(Collectors.toSet());
    }
}
