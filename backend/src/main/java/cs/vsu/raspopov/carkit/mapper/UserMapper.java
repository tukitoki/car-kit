package cs.vsu.raspopov.carkit.mapper;

import cs.vsu.raspopov.carkit.dto.user.UserDto;
import cs.vsu.raspopov.carkit.dto.user.UserResponse;
import cs.vsu.raspopov.carkit.entity.User;
import cs.vsu.raspopov.carkit.entity.enums.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserDto userDto, String password) {
        return User.builder()
                .fullName(userDto.getFullName())
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .password(password)
                .role(UserRole.valueOf(userDto.getRole()))
                .build();
    }

    public UserDto toDto(User user) {

        return UserDto.builder()
                .id(user.getId())
                .role(user.getRole().name())
                .fullName(user.getFullName())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    public UserResponse toResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .role(user.getRole().name())
                .fullName(user.getFullName())
                .build();
    }
}
