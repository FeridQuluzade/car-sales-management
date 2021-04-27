package az.turbo.backend.supplies.domain;

import az.turbo.backend.shared.PostgreDbService;
import az.turbo.backend.supplies.domain.model.Supply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupplyRepository {
    private PostgreDbService postgreDbService;

    @Autowired
    public SupplyRepository(PostgreDbService postgreDbService) {
        this.postgreDbService = postgreDbService;
    }

    public List<Supply> findAll(){

        try{
            List<Supply> supplies=new ArrayList<>();

            Connection connection= postgreDbService.getConnection();

            String query="select id,name from supplies";
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                long id= resultSet.getLong("id");
                String name= resultSet.getString("name");
                supplies.add(new Supply(id,name));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
            return supplies;
        }catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

public Optional<Supply> findById(long id){
        try{
            Optional<Supply> optionalSupply=Optional.empty();

            Connection connection= postgreDbService.getConnection();
            String query="Select * from supplies where id=?";

            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setLong(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();

            if (resultSet.next()){
                String name=resultSet.getString("name");
                Supply supply=new Supply(id,name);
                optionalSupply=Optional.of(supply);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();

            return optionalSupply;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
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

    public void update(Supply supply){
        try{
            Connection connection= postgreDbService.getConnection();

            String query="UPDATE supplies set name=?,updated_by=?,updated_date=?"+"where  id=?";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,supply.getName());
            preparedStatement.setLong(2,supply.getUpdatedBy());
            preparedStatement.setTimestamp(3,Timestamp.valueOf(supply.getUpdatedDate()));
            preparedStatement.setLong(4,supply.getId());
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        }catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
