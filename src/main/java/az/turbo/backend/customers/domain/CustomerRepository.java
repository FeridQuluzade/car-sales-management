package az.turbo.backend.customers.domain;

import az.turbo.backend.customers.domain.model.Customer;
import az.turbo.backend.shared.PostgreDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerRepository {
    private PostgreDbService postgreDbService;

    @Autowired
    public CustomerRepository(PostgreDbService postgreDbService) {
        this.postgreDbService = postgreDbService;
    }

    public List<Customer> findAll() {
        try {
            List<Customer> customers = new ArrayList<>();

            Connection connection = postgreDbService.getConnection();
            String query = "SELECT id, fullName, phone, email FROM customers";

            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("fullName");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                customers.add(new Customer(id, name, phone, email));
            }

            resultSet.close();
            ps.close();
            connection.close();

            return customers;

        } catch (SQLException throwables) {
            throw new RuntimeException(throwables.getMessage());
        }
    }
}
