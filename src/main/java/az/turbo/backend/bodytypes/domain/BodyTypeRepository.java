package az.turbo.backend.bodytypes.domain;


import az.turbo.backend.bodytypes.domain.model.BodyType;
import az.turbo.backend.users.application.exception.UserNotFoundException;
import az.turbo.backend.users.domain.UserRepository;
import az.turbo.backend.users.domain.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BodyTypeRepository {

    private final String URL = "jdbc:postgresql://localhost/turboaz";
    private final String USER = "postgres";
    private final String PASSWORD = "123456";
    private final String DRIVER_NAME = "org.postgresql.Driver";

    public List<BodyType>findAll(){
        try {
        List<BodyType>bodyTypes= new ArrayList<>();
        Class.forName(DRIVER_NAME);

        Connection connection= DriverManager.getConnection(URL,USER,PASSWORD);
        String query="select id,name from bodytypes";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        ResultSet resultSet= preparedStatement.executeQuery();
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String name=resultSet.getString("name");
            bodyTypes.add(new BodyType(id,name));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return bodyTypes;
        }
            catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e.getMessage());
            }

    }

    public  long create(BodyType bodyType){
        try{
            Class.forName(DRIVER_NAME);
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            String  query="insert into bodytypes(name,created_by,created_date)"+
                    "values(?,?,?) returning id";
                PreparedStatement preparedStatement= connection.prepareStatement(query);
                preparedStatement.setString(1,bodyType.getName());
                preparedStatement.setLong(2,bodyType.getCreatedBy());
                preparedStatement.setTimestamp(3, Timestamp.valueOf(bodyType.getCreatedDate()));
                ResultSet resultSet= preparedStatement.executeQuery();
                resultSet.next();
                long id = resultSet.getLong(1);
                resultSet.close();
                preparedStatement.close();
                connection.close();
                return  id;

        } catch (ClassNotFoundException e) {
          throw new RuntimeException(e.getMessage());
        } catch (SQLException e) {
           throw  new RuntimeException(e.getMessage());
        }
    }
}
