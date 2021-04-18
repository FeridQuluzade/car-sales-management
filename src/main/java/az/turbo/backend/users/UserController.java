package az.turbo.backend.users;

import az.turbo.backend.users.application.UserService;
import az.turbo.backend.users.application.dto.UserDto;
import az.turbo.backend.users.application.dto.UserSignInDto;
import az.turbo.backend.users.application.dto.UserTokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
}