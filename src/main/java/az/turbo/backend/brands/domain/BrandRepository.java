package az.turbo.backend.brands.domain;

import az.turbo.backend.brands.domain.model.Brand;
import az.turbo.backend.shared.PostgreDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class BrandRepository {
    private PostgreDbService postgreDbService;

    @Autowired
    public BrandRepository(PostgreDbService postgreDbService) {
        this.postgreDbService = postgreDbService;
    }

    public List<Brand> findAll() {
        try {
            List<Brand> brands = new ArrayList<>();

            Connection connection = postgreDbService.getConnection();
            String query = "Select  id,name from brands ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                brands.add(new Brand(id, name));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
            return brands;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public long create(Brand brand) {
        try {
            Connection connection = postgreDbService.getConnection();
            String query = "insert into brands(name,created_by,created_date)" +
                    "values(?,?,?) returning id";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, brand.getName());
            preparedStatement.setLong(2, brand.getCreatedBy());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(brand.getCreatedDate()));
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
