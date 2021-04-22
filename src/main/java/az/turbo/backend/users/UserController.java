package az.turbo.backend.users;

import az.turbo.backend.shared.UserContextHolder;
import az.turbo.backend.users.application.UserService;
import az.turbo.backend.users.application.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/retrieve-all")
    @ResponseBody
    public List<UserDto> retrieveAll() {
        return userService.retrieveAll();
    }

    @PostMapping(value = "/sign-in")
    @ResponseBody
    public UserTokenDto signIn(@Valid @RequestBody UserSignInDto userSignInDto) {
        return null;
    }

    @GetMapping(value = "/retrieve-by-id/{id}")
    @ResponseBody
    UserDto retrieveById(@PathVariable long id) {
        return userService.retrieveById(id);
    }

    @GetMapping(value = "/retrieve-by-email/{email}")
    @ResponseBody
    UserPasswordDto retrieveByEmail(@PathVariable String email) {
        return userService.retrieveByEmail(email);
    }

    @PostMapping(value = "/create")
    long create(@Valid @RequestBody UserCreateDto userCreateDto) {
        userCreateDto.setCreatedBy(UserContextHolder.getUserId());
        userCreateDto.setCreatedDate(LocalDateTime.now());
        return userService.create(userCreateDto);
    }

    @PostMapping(value = "/create-all")
    Set<Long> createAll(@Valid @RequestBody List<UserCreateDto> userCreateDtoList) {
        userCreateDtoList.forEach(u -> {
            u.setCreatedBy(UserContextHolder.getUserId());
            u.setCreatedDate(LocalDateTime.now());
        });
        return userService.createAll(userCreateDtoList);
    }

    @PutMapping(value = "/update")
    void update(@Valid @RequestBody UserUpdateDto userUpdateDto) {
        userUpdateDto.setUpdatedBy(UserContextHolder.getUserId());
        userUpdateDto.setUpdatedDate(LocalDateTime.now());
        userService.update(userUpdateDto);
    }

    @GetMapping(value = "/update-password{id, password}")
    @ResponseBody
    void updatePassword(@PathVariable long id, String password) {
        userService.updatePassword(id, password);
    }

    @GetMapping(value = "/update-refresh-token{id, refreshToken}")
    @ResponseBody
    void updateRefreshToken(@PathVariable long id, String refreshToken) {
        userService.updateRefreshToken(id, refreshToken);
    }

    @DeleteMapping(value = "/delete/{id}")
    void deleteById(@PathVariable long id) {
        userService.deleteById(id, UserContextHolder.getUserId(), LocalDateTime.now());
    }

    @DeleteMapping(value = "/delete-all/{ids}")
    void deleteAll(@PathVariable Set<Long> ids) {
        LocalDateTime deletedDate = LocalDateTime.now();
        userService.deleteAll(ids, UserContextHolder.getUserId(), deletedDate);
    }
}