package az.turbo.backend.users.application;

import az.turbo.backend.users.application.dto.UserCreateDto;
import az.turbo.backend.users.application.dto.UserDto;
import az.turbo.backend.users.application.dto.UserUpdateDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface UserService {
    List<UserDto> retrieveAll();

    UserDto retrieveByEmail(String email);

    long create(UserCreateDto userCreateDto);

    void update(UserUpdateDto UserUpdateDto);

    void deleteById(long id, long deletedBy, LocalDateTime deletedDate);

    void deleteAll(Set<Long> ids, long deletedBy, LocalDateTime deletedDate);
}
