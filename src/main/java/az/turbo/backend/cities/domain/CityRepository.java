package az.turbo.backend.cities.domain;

import az.turbo.backend.cities.domain.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityRepository {
    private final String URL = "jdbc:postgresql://localhost/turboaz";
    private final String USER = "postgres";
    private final String PASSWORD = "Pass1234";
    private final String DRIVER_NAME = "org.postgresql.Driver";

    public List<City> findAll() {
        try {
            List<City> cities = new ArrayList<>();

            Class.forName(DRIVER_NAME);

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "SELECT id, name FROM cities";

            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                cities.add(new City(id, name));
            }
            resultSet.close();
            ps.close();
            connection.close();
            return cities;

        } catch (SQLException throwables) {
            throw new RuntimeException(throwables.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public long create(City city){
        try {
            Class.forName(DRIVER_NAME);

            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            String query ="INSERT INTO cities (name, created_by, created_date) " +
                    "values (?,?,?) returning id;";

            PreparedStatement ps= connection.prepareStatement(query);

            ps.setString(1,city.getName());
            ps.setLong(2,city.getCreatedBy());
            ps.setTimestamp(3, Timestamp.valueOf(city.getCreatedDate()));

            ResultSet resultSet=ps.executeQuery();
            resultSet.next();

            long id=resultSet.getLong(1);

            resultSet.close();
            ps.close();
            connection.close();

            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
