package az.turbo.backend.supplies.domain;

import az.turbo.backend.shared.PostgreDbService;
import az.turbo.backend.supplies.domain.model.Supply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class SupplyRepository {
    private PostgreDbService postgreDbService;

    @Autowired
    public SupplyRepository(PostgreDbService postgreDbService) {
        this.postgreDbService = postgreDbService;
    }

    public long create(Supply supply) {

        try {
            Connection connection = postgreDbService.getConnection();
            String query = "insert into supplies(name,created_by,created_date)"
                    + "values(?,?,?) returning id";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, supply.getName());
            preparedStatement.setLong(2, supply.getCreatedBy());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(supply.getCreatedDate()));
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
