package az.turbo.backend.currencies.domain;

import az.turbo.backend.currencies.domain.model.Currency;
import az.turbo.backend.shared.PostgreDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyRepository {
    private PostgreDbService postgreDbService;

    @Autowired
    public CurrencyRepository(PostgreDbService postgreDbService) {
        this.postgreDbService = postgreDbService;
    }

    public List<Currency> findAll() {
        try {
            List<Currency> currencies = new ArrayList<>();

            Connection connection = postgreDbService.getConnection();
            String query = "select id,name from currencies";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                currencies.add(new Currency(id, name));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return currencies;

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public long create(Currency currency) {
        try {
            Connection connection = postgreDbService.getConnection();
            String query = "insert into currencies(name,created_by,created_date)" +
                    "values(?,?,?) returning id";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, currency.getName());
            preparedStatement.setLong(2, currency.getCreatedBy());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(currency.getCreatedDate()));
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

    public void update(Currency currency) {
        try {
            Connection connection = postgreDbService.getConnection();

            String query = "Update currencies " +
                    "SET name=?,updated_by=?,updated_date=?" +
                    "where id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, currency.getName());
            preparedStatement.setLong(2, currency.getUpdatedBy());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(currency.getUpdatedDate()));
            preparedStatement.setLong(4, currency.getId());
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

       public void DeleteById(long id, long deleteBy, LocalDateTime deletedDate) {
           try {
               Connection connection = postgreDbService.getConnection();

               String query = "UPDATE currencies SET is_deleted= cast(? as bit),deleted_by=?,deleted_date=?" +
                       "where id=?";

               PreparedStatement preparedStatement = connection.prepareStatement(query);
               preparedStatement.setString(1, "1");
               preparedStatement.setLong(2, deleteBy);
               preparedStatement.setTimestamp(3,Timestamp.valueOf(deletedDate));
               preparedStatement.setLong(4,id);

               preparedStatement.executeUpdate();
               preparedStatement.close();
               connection.close();
           } catch (SQLException e) {
               throw new RuntimeException(e.getMessage());
           }



       }
}
