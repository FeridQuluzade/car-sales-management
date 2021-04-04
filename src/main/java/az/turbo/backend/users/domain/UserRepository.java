package az.turbo.backend.users.domain;

import az.turbo.backend.users.domain.model.Gender;
import az.turbo.backend.users.domain.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final String URL = "jdbc:postgresql://localhost/turboaz";
    private final String USER = "postgres";
    private final String PASSWORD = "Pass1234";
    private final String DRIVER_NAME = "org.postgresql.Driver";

    public List<User> findAll() {
        try {
            List<User> users = new ArrayList<>();

            Class.forName(DRIVER_NAME);

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "select id, first_name, last_name, gender, email, password from users";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int gender = resultSet.getInt("gender");
                String email = resultSet.getString("email");
                String hashPassword = resultSet.getString("password");

                users.add(new User(id,
                        firstName,
                        lastName,
                        gender == 0 ? Gender.MALE : Gender.FEMALE,
                        email,
                        hashPassword));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();

            return users;

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public User findByEmail(String filterEmail) {
        try {
            Class.forName(DRIVER_NAME);

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "select * from users WHERE email=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, filterEmail);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            long id = resultSet.getLong("id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            int gender = resultSet.getInt("gender");
            String email = resultSet.getString("email");
            String hashPassword = resultSet.getString("password");

            User user = new User(id,
                    firstName,
                    lastName,
                    gender == 0 ? Gender.MALE : Gender.FEMALE,
                    email,
                    hashPassword);

            resultSet.close();
            preparedStatement.close();
            connection.close();

            return user;

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public long create(User user) {
        try {
            Class.forName(DRIVER_NAME);

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "insert into users(first_name, last_name, gender, email, password, created_by, created_date)" +
                    "values(?,?,?,?,?,?,?) returning id";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setInt(3, user.getGender().ordinal());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setLong(6, user.getCreatedBy());
            preparedStatement.setTimestamp(7, Timestamp.valueOf(user.getCreatedDate()));

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            long id = resultSet.getLong(1);

            resultSet.close();
            preparedStatement.close();
            connection.close();

            return id;

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void update(User user) {
        try {
            Class.forName(DRIVER_NAME);

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            String query = "update users " +
                    "SET first_name=?, last_name=?, gender=?, email=?, updated_by=?, updated_date=? " +
                    "where id=?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setInt(3, user.getGender().ordinal());
            ps.setString(4, user.getEmail());
            ps.setLong(5, user.getUpdatedBy());
            ps.setTimestamp(6, Timestamp.valueOf(user.getUpdatedDate()));
            ps.setLong(7, user.getId());

            ps.executeUpdate();

            ps.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
