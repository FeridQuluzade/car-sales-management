package az.turbo.backend.users.application;

import az.turbo.backend.users.application.dto.UserCreateDto;
import az.turbo.backend.users.application.dto.UserDto;
import az.turbo.backend.users.application.dto.UserUpdateDto;

import java.util.List;

public interface UserService {
    List<UserDto> retrieveAll();

    UserDto retrieveByEmail(String email);

    long create(UserCreateDto userCreateDto);

    void update(UserUpdateDto UserUpdateDto);




}
