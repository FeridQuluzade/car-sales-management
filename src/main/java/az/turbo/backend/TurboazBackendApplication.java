package az.turbo.backend;

import az.turbo.backend.bodytypes.application.BodyTypeService;
import az.turbo.backend.bodytypes.application.BodyTypeServiceImpl;
import az.turbo.backend.bodytypes.application.dto.BodyCreateDto;
import az.turbo.backend.users.application.UserService;
import az.turbo.backend.users.application.UserServiceImpl;
import az.turbo.backend.users.application.dto.UserCreateDto;
import az.turbo.backend.users.application.dto.UserDto;
import az.turbo.backend.users.application.dto.UserUpdateDto;
import az.turbo.backend.users.domain.model.Gender;
import az.turbo.backend.users.domain.model.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.expression.Sets;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        BodyTypeService bodyTypeService= new BodyTypeServiceImpl();
        BodyCreateDto bodyCreateDto= new BodyCreateDto();
        bodyCreateDto.setName("SUV");
        bodyCreateDto.setCreatedBy(22L);
        bodyCreateDto.setCreatedDate(LocalDateTime.now());
        System.out.println(bodyTypeService.create(bodyCreateDto));
    }
}
