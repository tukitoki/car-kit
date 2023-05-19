package cs.vsu.raspopov.carkit.service.impl;

import cs.vsu.raspopov.carkit.dto.user.UserDto;
import cs.vsu.raspopov.carkit.entity.User;
import cs.vsu.raspopov.carkit.entity.enums.UserRole;
import cs.vsu.raspopov.carkit.exception.UserException;
import cs.vsu.raspopov.carkit.mapper.UserMapper;
import cs.vsu.raspopov.carkit.repository.UserRepository;
import cs.vsu.raspopov.carkit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public UserDto findUserById(Long id) {
        User user = getUserById(id);
        return userMapper.toDto(user);
    }

    @Override
    public void createUser(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw UserException.CODE.USERNAME_ALREADY_PRESENT.get();
        }
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw UserException.CODE.EMAIL_ALREADY_PRESENT.get();
        }

        String password = passwordEncoder.encode(userDto.getPassword());

        User user = userMapper.toEntity(userDto, password);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(UserDto userDto, Long id) {
        User oldUser = getUserById(id);

        var optionalUser = userRepository.findByUsername(userDto.getUsername());
        if (optionalUser.isPresent() &&
                !optionalUser.get().getUsername().equals(oldUser.getUsername())) {
            throw UserException.CODE.USERNAME_ALREADY_PRESENT.get();
        }

        optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isPresent() &&
                !optionalUser.get().getUsername().equals(oldUser.getUsername())) {
            throw UserException.CODE.EMAIL_ALREADY_PRESENT.get();
        }

        String password = userDto.getPassword() == null
                ? null
                : passwordEncoder.encode(userDto.getPassword());

        User newUser = userMapper.toEntity(userDto, password);
        if (password == null) {
            BeanUtils.copyProperties(newUser, oldUser, "id", "password");
        } else {
            BeanUtils.copyProperties(newUser, oldUser, "id");
        }
        userRepository.save(oldUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = getUserById(id);

        userRepository.delete(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserException.CODE.ID_NOT_FOUND::get);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(UserException.CODE.USERNAME_NOT_FOUND::get);
    }

    private List<String> getAllRoles() {
        return Arrays.stream(UserRole.values())
                .map(Enum::name)
                .toList();
    }
}
