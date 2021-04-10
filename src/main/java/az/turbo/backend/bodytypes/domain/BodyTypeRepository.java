package az.turbo.backend.bodytypes.domain;

import az.turbo.backend.bodytypes.domain.model.BodyType;

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

    public List<BodyType> findAll() {
        try {
            List<BodyType> bodyTypes = new ArrayList<>();
            Class.forName(DRIVER_NAME);

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "select id, name from bodytypes";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                bodyTypes.add(new BodyType(id, name));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return bodyTypes;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<BodyType> findById(long id) {
        try {
            Optional<BodyType> optionalBodyType = Optional.empty();

            Class.forName(DRIVER_NAME);

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "SELECT * FROM bodytypes WHERE id=?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                BodyType bodyType = new BodyType(id, name);
                optionalBodyType = Optional.of(bodyType);
            }

            resultSet.close();
            ps.close();
            connection.close();

            return optionalBodyType;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public long create(BodyType bodyType) {
        try {
            Class.forName(DRIVER_NAME);
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "insert into bodytypes(name, created_by, created_date) " +
                    "values(?,?,?) returning id";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, bodyType.getName());
            preparedStatement.setLong(2, bodyType.getCreatedBy());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(bodyType.getCreatedDate()));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            long id = resultSet.getLong(1);
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return id;

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void update(BodyType bodyType) {
        try {
            Class.forName(DRIVER_NAME);

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            String query = "Update bodytypes " +
                    "SET name=?, updated_by=?, updated_date=? " +
                    "where id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, bodyType.getName());
            ps.setLong(2, bodyType.getUpdatedBy());
            ps.setTimestamp(3, Timestamp.valueOf(bodyType.getUpdatedDate()));
            ps.setLong(4, bodyType.getId());
            ps.executeUpdate();

            ps.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void DeleteById(long id, long deleteBy, LocalDateTime deletedDate) {
        try {
            Class.forName(DRIVER_NAME);
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            String query = "UPDATE bodytypes SET is_deleted= cast(? as bit),deleted_by=?,deleted_date=?" +
                    "where id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "1");
            preparedStatement.setLong(2, deleteBy);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(deletedDate));
            preparedStatement.setLong(4, id);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
