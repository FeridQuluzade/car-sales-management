package az.turbo.backend.users.domain;

import az.turbo.backend.shared.PostgreDbService;
import az.turbo.backend.users.domain.model.Gender;
import az.turbo.backend.users.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserRepository {
    private PostgreDbService postgreDbService;

    @Autowired
    public UserRepository(PostgreDbService postgreDbService) {
        this.postgreDbService = postgreDbService;
    }

    public List<User> findAll() {
        try {
            List<User> users = new ArrayList<>();

            Connection connection = postgreDbService.getConnection();
            String query = "select id, first_name, last_name, gender, email, password from users " +
                    "where is_deleted = bit'0'";

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
        }
    }

    public Optional<User> findById(long id) {
        try {
            Optional<User> optionalUser = Optional.empty();

            Connection connection = postgreDbService.getConnection();
            String query = "select * from users WHERE id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
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

                optionalUser = Optional.of(user);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

            return optionalUser;

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<User> findByEmail(String email) {
        try {
            Optional<User> optionalUser = Optional.empty();

            Connection connection = postgreDbService.getConnection();
            String query = "select * from users WHERE email=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                long id = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int gender = resultSet.getInt("gender");
                String hashPassword = resultSet.getString("password");

                User user = new User(id,
                        firstName,
                        lastName,
                        gender == 0 ? Gender.MALE : Gender.FEMALE,
                        email,
                        hashPassword);

                optionalUser = Optional.of(user);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

            return optionalUser;

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public long create(User user) {
        try {
            Connection connection = postgreDbService.getConnection();
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
        }
    }

    public void update(User user) {
        try {
            Connection connection = postgreDbService.getConnection();

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

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void updatePassword(long id, String password) {
        try {
            Connection connection = postgreDbService.getConnection();

            String query = "update users " +
                    "SET password=? " +
                    "where id=?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, password);
            ps.setLong(2, id);

            ps.executeUpdate();

            ps.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void updateRefreshToken(long id, String refreshToken) {
        try {
            Connection connection = postgreDbService.getConnection();

            String query = "update users " +
                    "SET refresh_token=? " +
                    "where id=?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, refreshToken);
            ps.setLong(2, id);

            ps.executeUpdate();

            ps.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteById(long id, long deletedBy, LocalDateTime deletedDate) {
        try {
            Connection connection = postgreDbService.getConnection();

            String query = "update users " +
                    "SET is_deleted=cast(? as bit), deleted_by=?, deleted_date=? " +
                    "where id=?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, "1");
            ps.setLong(2, deletedBy);
            ps.setTimestamp(3, Timestamp.valueOf(deletedDate));
            ps.setLong(4, id);

            ps.executeUpdate();

            ps.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteAll(Set<Long> ids, long deletedBy, LocalDateTime deletedDate) {
        try {
            Connection connection = postgreDbService.getConnection();
            connection.setAutoCommit(false);

            String query = "update users " +
                    "SET is_deleted=cast(? as bit), deleted_by=?, deleted_date=? " +
                    "where id=?";

            PreparedStatement ps = connection.prepareStatement(query);

            for (long id : ids) {
                ps.setString(1, "1");
                ps.setLong(2, deletedBy);
                ps.setTimestamp(3, Timestamp.valueOf(deletedDate));
                ps.setLong(4, id);
                ps.addBatch();
            }

            ps.executeBatch();

            connection.commit();

            ps.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
