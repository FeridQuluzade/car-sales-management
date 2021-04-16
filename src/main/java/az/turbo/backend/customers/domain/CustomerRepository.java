package az.turbo.backend.customers.domain;

import az.turbo.backend.customers.domain.model.Customer;
import az.turbo.backend.shared.PostgreDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<Customer> findById(long id) {
        try {
            Optional<Customer> optionalCustomer = Optional.empty();

            Connection connection = postgreDbService.getConnection();

            String query = "SELECT * FROM customers " +
                    "WHERE id=?;";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("fullName");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");

                Customer customer = new Customer(id, name, phone, email);
                optionalCustomer = Optional.of(customer);
            }

            resultSet.close();
            ps.close();
            connection.close();

            return optionalCustomer;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public long create(Customer customer) {
        try {
            Connection connection = postgreDbService.getConnection();

            String query = "INSERT INTO customers (fullName, phone, email, created_by, created_date) " +
                    "values (?,?,?) returning id;";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, customer.getFullName());
            ps.setString(2, customer.getPhone());
            ps.setString(3, customer.getEmail());
            ps.setLong(4, customer.getCreatedBy());
            ps.setTimestamp(5, Timestamp.valueOf(customer.getCreatedDate()));

            ResultSet resultSet = ps.executeQuery();
            resultSet.next();

            long id = resultSet.getLong(1);

            resultSet.close();
            ps.close();
            connection.close();

            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void update(Customer customer) {
        try {
            Connection connection = postgreDbService.getConnection();

            String query = "UPDATE customers " +
              "SET fullName=?, phone=?, email=?, updated_by=?, updated_date=? " +
              "WHERE id=?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1,customer.getFullName());
            ps.setString(2,customer.getPhone());
            ps.setString(3,customer.getPhone());
            ps.setLong(4,customer.getUpdatedBy());
            ps.setTimestamp(5, Timestamp.valueOf(customer.getUpdatedDate()));
            ps.setLong(6,customer.getId());

            ps.executeUpdate();

            ps.close();
            connection.close();

        } catch (SQLException e) {
           throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteById(long customerID, long deleteByID, LocalDateTime deletedDate){
        try{
            Connection connection = postgreDbService.getConnection();

            String query = "UPDATE customers " +
                    "SET is_deleted=cast(? as bit), deleted_by=?, deleted_date=? " +
                    "WHERE id=? ;";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,"1");
            ps.setLong(2, deleteByID);
            ps.setTimestamp(3, Timestamp.valueOf(deletedDate));
            ps.setLong(4,customerID);

            ps.executeUpdate();

            ps.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
