package az.turbo.backend;

import az.turbo.backend.users.application.UserService;
import az.turbo.backend.users.application.UserServiceImpl;
import az.turbo.backend.users.application.dto.UserCreateDto;
import az.turbo.backend.users.application.dto.UserDto;
import az.turbo.backend.users.application.dto.UserUpdateDto;
import az.turbo.backend.users.domain.model.Gender;
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
        userCreateDto.setFirstName("Elcin");
        userCreateDto.setLastName("Kazimov");
        userCreateDto.setGender(Gender.MALE);
        userCreateDto.setEmail("elcin.kazimov@gmail.com");
        userCreateDto.setPassword("dhhffkjhsjkhfhskfhsf");
        userCreateDto.setCreatedBy(2);
        userCreateDto.setCreatedDate(LocalDateTime.now());

//        long id = userService.create(userCreateDto);
//        System.out.println(id);
        UserCreateDto userCreateDto2 = new UserCreateDto();
        userCreateDto2.setFirstName("Yalcin");
        userCreateDto2.setLastName("Kazimov");
        userCreateDto2.setGender(Gender.MALE);
        userCreateDto2.setEmail("yalcin.kazimov3@gmail.com");
        userCreateDto2.setPassword("dsfsdfs");
        userCreateDto2.setCreatedBy(2);
        userCreateDto2.setCreatedDate(LocalDateTime.now());

        // System.out.println(userService.create(userCreateDto2));


        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setId(5);
        userUpdateDto.setFirstName("Yalchin");
        userUpdateDto.setLastName("Kazimov");
        userUpdateDto.setGender(Gender.MALE);
        userUpdateDto.setEmail("yalcin.kazimov@gmail.com");
        userUpdateDto.setPassword("dsfsasasdfs");
        userUpdateDto.setUpdatedBy(2);
        userUpdateDto.setUpdatedDate(LocalDateTime.now());

       // System.out.println(userService.update(userUpdateDto));


        List<UserDto> users = userService.retrieveAll();

        for (UserDto user : users) {
            System.out.println(user);
        }

        System.out.println("---------------------------------------------");

        List<UserDto> userFind=userService.findByEmail("yunus.kazimov3@gmail.com");
        for (UserDto user : userFind) {
            System.out.println(user);
        }
    }
}
