package az.turbo.backend;

import az.turbo.backend.users.application.UserService;
import az.turbo.backend.users.application.UserServiceImpl;
import az.turbo.backend.users.application.dto.UserCreateDto;
import az.turbo.backend.users.application.dto.UserDto;
import az.turbo.backend.users.application.dto.UserUpdateDto;
import az.turbo.backend.users.domain.model.Gender;
import az.turbo.backend.users.domain.model.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Arrays;
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

        System.out.println("---------------------------------------------");

        UserDto userByEmail = userService.retrieveByEmail("yunus.kazimov3@gmail.com");
        System.out.println(userByEmail);
    }
}

//class m2{
//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        String url = "jdbc:postgresql://localhost/turboaz";
//        String user = "postgres";
//        String password = "Pass1234";
//        String driverName = "org.postgresql.Driver";
//
//
//        UserCreateDto userCreateDto2 = new UserCreateDto();
//        userCreateDto2.setFirstName("Ferid");
//        userCreateDto2.setLastName("Quluzade");
//        userCreateDto2.setGender(Gender.MALE);
//        userCreateDto2.setEmail("ferid.quluzade@gmail.com");
//        userCreateDto2.setPassword("Pass1234");
//        userCreateDto2.setCreatedBy(2);
//        userCreateDto2.setCreatedDate(LocalDateTime.now());
//
//        UserCreateDto userCreateDto3 = new UserCreateDto();
//        userCreateDto3.setFirstName("Eldar");
//        userCreateDto3.setLastName("Veliyev");
//        userCreateDto3.setGender(Gender.MALE);
//        userCreateDto3.setEmail("eldar.veliyev@gmail.com");
//        userCreateDto3.setPassword("Pass12345");
//        userCreateDto3.setCreatedBy(2);
//        userCreateDto3.setCreatedDate(LocalDateTime.now());
//
//
//        List<UserCreateDto> bulkInserts = Arrays.asList(userCreateDto2, userCreateDto3);
//
//        Class.forName(driverName);
//        Connection connection = DriverManager.getConnection(url, user, password);
//        connection.setAutoCommit(false);   //???????????????????????
//        String query = "insert into users(first_name, last_name, gender, email, password, created_by, created_date)" +
//                "values(?,?,?,?,?,?,?)";
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
//        for (UserCreateDto u : bulkInserts) {
//            preparedStatement.setString(1, u.getFirstName());
//            preparedStatement.setString(2, u.getLastName());
//            preparedStatement.setInt(3, u.getGender().ordinal());
//            preparedStatement.setString(4, u.getEmail());
//            preparedStatement.setString(5, u.getPassword());
//            preparedStatement.setLong(6, u.getCreatedBy());
//            preparedStatement.setTimestamp(7, Timestamp.valueOf(u.getCreatedDate()));
//
//            preparedStatement.addBatch();//???????????????????????
//        }
//        int[] updatedRows = preparedStatement.executeBatch();//???????????????????????
//        System.out.println(updatedRows.length);
//        connection.commit();//???????????????????????
//        preparedStatement.close();
//        connection.close();
//    }
//}
//
//
//class m3{
//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        String url = "jdbc:postgresql://localhost/turboaz";
//        String user = "postgres";
//        String password = "Pass1234";
//        String driverName = "org.postgresql.Driver";
//
//// Burdaki UpdateDTO nu DeleteDTO kimi fikirlesin.
//        UserUpdateDto userUpdateDto = new UserUpdateDto();
//        userUpdateDto.setId(6);
//        userUpdateDto.setFirstName("Ferid");
//        userUpdateDto.setLastName("Quluzade");
//        userUpdateDto.setGender(Gender.MALE);
//        userUpdateDto.setEmail("ferid.quluzade@gmail.com");
//        userUpdateDto.setUpdatedBy(2);
//        userUpdateDto.setUpdatedDate(LocalDateTime.now());
//
//        UserUpdateDto userUpdateDto2 = new UserUpdateDto();
//        userUpdateDto2.setId(7);
//        userUpdateDto2.setFirstName("Eldar");
//        userUpdateDto2.setLastName("Veliyev");
//        userUpdateDto2.setGender(Gender.MALE);
//        userUpdateDto2.setEmail("eldar.veliyev@gmail.com");
//        userUpdateDto2.setUpdatedBy(2);
//        userUpdateDto2.setUpdatedDate(LocalDateTime.now());
//
//        List<UserUpdateDto> bulkDelete = Arrays.asList(userUpdateDto,userUpdateDto2);
//
//        Class.forName(driverName);
//        Connection connection = DriverManager.getConnection(url, user, password);
//        connection.setAutoCommit(false);   //???????????????????????
//        String query =  "update  users " + "set deleted_by=?,deleted_date=?,is_deleted=cast(? as bit  )"
//                + "where id=?";
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
//        for (UserUpdateDto u : bulkDelete) {
//            preparedStatement.setLong(4,u.getId());
//            preparedStatement.setLong(1,u.getUpdatedBy());
//            preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
//            preparedStatement.setString(3, "1");
//
//            preparedStatement.addBatch();//???????????????????????
//        }
//        int[] updatedRows = preparedStatement.executeBatch();//???????????????????????
//        System.out.println(updatedRows.length);
//        connection.commit();//???????????????????????
//        preparedStatement.close();
//        connection.close();
//    }
//}
