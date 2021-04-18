package az.turbo.backend.engineVolumes.domain;

import az.turbo.backend.engineVolumes.domain.model.EngineVolume;
import az.turbo.backend.shared.PostgreDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.RectangularShape;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class EngineVolumeRepository {
    private PostgreDbService postgreDbService;

    @Autowired
    public EngineVolumeRepository(PostgreDbService postgreDbService) {
        this.postgreDbService = postgreDbService;
    }

    public long create(EngineVolume engineVolume) {
        try {
            Connection connection = postgreDbService.getConnection();
            String query = "insert into enginevolumes(name,created_by,created_date)" +
                    "values(?,?,?) returning id";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, engineVolume.getValue());
            preparedStatement.setLong(2, engineVolume.getUpdatedBy());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(engineVolume.getUpdatedDate()));

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
