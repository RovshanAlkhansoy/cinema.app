package demo.cinema.app.mapper;

import demo.cinema.app.dto.request.UserRegisterRequest;
import demo.cinema.app.dto.response.UserRegisterResponse;
import demo.cinema.app.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "balance", constant = "100.0")
    User userRegisterRequestToUser(UserRegisterRequest request);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "message", constant = "User Registered successfully")
    @Mapping(source = "userId", target = "userId")
    UserRegisterResponse userToUserRegisterResponse(User user);

}
