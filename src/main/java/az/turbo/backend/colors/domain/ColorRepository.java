package az.turbo.backend.colors.domain;

import az.turbo.backend.colors.domain.model.Color;

import java.sql.*;

public class ColorRepository {
    private final String URL = "jdbc:postgresql://localhost/turboaz";
    private final String USER = "postgres";
    private final String PASSWORD = "123456";
    private final String DRIVER_NAME = "org.postgresql.Driver";

     public long create(Color color){
         try {
             Class.forName(DRIVER_NAME);
             Connection connection= DriverManager.getConnection(URL,USER,PASSWORD);
             String query="insert into colors(name,created_by,created_date)"+
                     "values(?,?,?) returning id";
             PreparedStatement preparedStatement= connection.prepareStatement(query);
             preparedStatement.setString(1,color.getName());
             preparedStatement.setLong(2,color.getCreatedBy());
             preparedStatement.setTimestamp(3, Timestamp.valueOf(color.getCreatedDate()));
             ResultSet resultSet= preparedStatement.executeQuery();
             resultSet.next();
             long id=resultSet.getLong(1);
             resultSet.close();
             preparedStatement.close();
             connection.close();
             return id;
         }  catch (ClassNotFoundException e) {
             throw new RuntimeException(e.getMessage());
         } catch (SQLException e) {
             throw new RuntimeException(e.getMessage());
         }
     }


}
