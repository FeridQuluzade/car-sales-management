package az.turbo.backend.colors.domain;

import az.turbo.backend.colors.domain.model.Color;
import az.turbo.backend.shared.PostgreDbService;
import org.graalvm.compiler.lir.alloc.lsra.LinearScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ColorRepository {
    private PostgreDbService postgreDbService;

    @Autowired
    public ColorRepository(PostgreDbService postgreDbService) {
        this.postgreDbService = postgreDbService;
    }

    public List<Color> findAll() {
        try {
            List<Color> colors = new ArrayList<>();
            Connection connection = postgreDbService.getConnection();
            String query = "select id,name from colors";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                colors.add(new Color(id, name));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return colors;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
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
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
