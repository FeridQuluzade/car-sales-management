package az.turbo.backend;

import az.turbo.backend.cities.application.CityServices;
import az.turbo.backend.cities.application.CityServicesImpl;
import az.turbo.backend.cities.application.dto.CityCreateDto;
import az.turbo.backend.cities.application.dto.CityUpdateDto;
import az.turbo.backend.users.application.UserService;
import az.turbo.backend.users.application.UserServiceImpl;
import az.turbo.backend.users.application.dto.UserCreateDto;
import az.turbo.backend.users.application.dto.UserDto;
import az.turbo.backend.users.application.dto.UserUpdateDto;
import az.turbo.backend.users.domain.model.Gender;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
public class TurboazBackendApplication {

    public static void main(String[] args) {
//UserTestMain();

        CityTestMain();

    }

    public static void CityTestMain() {
        CityServices cityServices = new CityServicesImpl();

        CityCreateDto city1 = new CityCreateDto();
        city1.setName("SUV");
        city1.setCreatedBy(4);
        city1.setCreatedDate(LocalDateTime.now());

      //  cityServices.create(city1);

        CityCreateDto city2 = new CityCreateDto();
        city2.setName("Sedan");
        city2.setCreatedBy(2);
        city2.setCreatedDate(LocalDateTime.now());

        //  cityServices.create(city2);

        cityServices.retrieveAll().stream().forEach(System.out::println);

        System.out.println("---------------------------------");

        CityUpdateDto cityUpdate1=new CityUpdateDto();
        cityUpdate1.setId(4L);
        cityUpdate1.setName("Universal");
        cityUpdate1.setUpdatedBy(5);
        cityUpdate1.setUpdatedDate(LocalDateTime.now());

        cityServices.update(cityUpdate1);

        cityServices.retrieveAll().stream().forEach(System.out::println);
    }

    public static void UserTestMain() {
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
        userUpdateDto.setFirstName("Yalcin");
        userUpdateDto.setLastName("Kazimov");
        userUpdateDto.setGender(Gender.MALE);
        userUpdateDto.setEmail("yalcin.kazimov@gmail.com");
        userUpdateDto.setUpdatedBy(2);
        userUpdateDto.setUpdatedDate(LocalDateTime.now());

        // System.out.println(userService.update(userUpdateDto));


        List<UserDto> users = userService.retrieveAll();

        for (UserDto user : users) {
            System.out.println(user);
        }
//
//        System.out.println("---------------------------------------------");
//
////        UserDto userByEmail = userService.retrieveByEmail("yunus.kazimov3@gmail.com");
////        System.out.println(userByEmail);
//
//        //userService.deleteById(3, 2, LocalDateTime.now());
//
        Set<Long> ids = Arrays.asList(6L, 8L, 9L).stream().collect(Collectors.toSet());
        userService.deleteAll(ids, 2, LocalDateTime.now());
    }
}
