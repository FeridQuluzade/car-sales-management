package az.turbo.backend;

import az.turbo.backend.users.application.UserService;
import az.turbo.backend.users.application.UserServiceImpl;
import az.turbo.backend.users.application.dto.UserCreateDto;
import az.turbo.backend.users.application.dto.UserDto;
import az.turbo.backend.users.domain.model.Gender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class TurboazBackendApplication {

    public static void main(String[] args) {
        //SpringApplication.run(TurboazBackendApplication.class, args);

        //update - Yunus
        //deleteById - Ferid
        //findById - Jalal,
        //findByEmail - Yunus
        //bulkInsert - Jalal
        //bulkDelete - Ferid

        UserService userService = new UserServiceImpl();

        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setFirstName("Yunus");
        userCreateDto.setLastName("Kazimov");
        userCreateDto.setGender(Gender.MALE);
        userCreateDto.setEmail("yunus.kazimov3@gmail.com");
        userCreateDto.setPassword("dhhffkjhsjkhfhskfhsf");
        userCreateDto.setCreatedBy(2);
        userCreateDto.setCreatedDate(LocalDateTime.now());

        long id = userService.create(userCreateDto);

        List<UserDto> users = userService.retrieveAll();

        for(UserDto user : users) {
            System.out.println(user);
        }
    }
}
