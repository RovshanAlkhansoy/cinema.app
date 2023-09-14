package demo.cinema.app.service.impl;

import demo.cinema.app.dto.request.UserRegisterRequest;
import demo.cinema.app.dto.response.UserRegisterResponse;
import demo.cinema.app.enums.Role;
import demo.cinema.app.mapper.UserMapper;
import demo.cinema.app.repository.UserRepository;
import demo.cinema.app.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<UserRegisterResponse> registerNewUser(UserRegisterRequest userRegisterRequest) {
        long numberOfUsers = userRepository.count();
        Role role = numberOfUsers < 2 ? Role.ADMIN : Role.USER;
        return Optional.of(userMapper.userRegisterRequestToUser(userRegisterRequest))
                .map(user -> {
                    user.setRole(role);
                    return user;
                })
                .map(userRepository::save)
                .map(userMapper::userToUserRegisterResponse);
    }

}


