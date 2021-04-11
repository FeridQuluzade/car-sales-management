package az.turbo.backend.cities.domain;

import az.turbo.backend.cities.domain.model.City;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<City> findById(long id) {
        try {
            Optional<City> optionalCity = Optional.empty();

            Class.forName(DRIVER_NAME);

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "SELECT * FROM cities WHERE id=?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                City city = new City(id, name);
                optionalCity = Optional.of(city);
            }

            resultSet.close();
            ps.close();
            connection.close();

            return optionalCity;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public long create(City city) {
        try {
            Class.forName(DRIVER_NAME);

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "INSERT INTO cities (name, created_by, created_date) " +
                    "values (?,?,?) returning id;";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, city.getName());
            ps.setLong(2, city.getCreatedBy());
            ps.setTimestamp(3, Timestamp.valueOf(city.getCreatedDate()));

            ResultSet resultSet = ps.executeQuery();
            resultSet.next();

            long id = resultSet.getLong(1);

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

    public void update(City city) {
        try {
            Class.forName(DRIVER_NAME);

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            String query = "UPDATE cities " +
                    "SET name=?, updated_by=?, updated_date=? " +
                    "WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, city.getName());
            ps.setLong(2, city.getUpdatedBy());
            ps.setTimestamp(3, Timestamp.valueOf(city.getUpdatedDate()));
            ps.setLong(4, city.getId());

            ps.executeUpdate();

            ps.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteById(long id, long deleteBy, LocalDateTime deletedDate){
        try{
            Class.forName(DRIVER_NAME);

            Connection connection= DriverManager.getConnection(URL, USER, PASSWORD);

            String query ="UPDATE cities " +
                    "SET is_deleted=cast(? as bit), deleted_by=?, deleted_date=? " +
                    "WHERE id=?";

            PreparedStatement ps =connection.prepareStatement(query);
            ps.setString(1,"1");
            ps.setLong(2,deleteBy);
            ps.setTimestamp(3, Timestamp.valueOf(deletedDate));
            ps.setLong(4,id);

            ps.executeUpdate();

            ps.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
