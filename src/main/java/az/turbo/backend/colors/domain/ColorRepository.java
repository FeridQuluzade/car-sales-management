package az.turbo.backend.colors.domain;

import az.turbo.backend.colors.domain.model.Color;
import az.turbo.backend.shared.PostgreDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
@Service
public class ColorRepository {
    private PostgreDbService postgreDbService;
    @Autowired
    public ColorRepository(PostgreDbService postgreDbService){
        this.postgreDbService=postgreDbService;
    }

    public long create(Color color) {
        try {

            Connection connection = postgreDbService.getConnection();
            String query = "insert into colors(name,created_by,created_date)" +
                    "values(?,?,?) returning id";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, color.getName());
            preparedStatement.setLong(2, color.getCreatedBy());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(color.getCreatedDate()));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            long id = resultSet.getLong(1);
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return id;
        }  catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
