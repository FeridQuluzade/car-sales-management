package az.turbo.backend.cities.application;

import az.turbo.backend.cities.application.dto.CityCreateDto;
import az.turbo.backend.cities.application.dto.CityDto;
import az.turbo.backend.cities.application.dto.CityUpdateDto;

import java.time.LocalDateTime;
import java.util.List;

public interface CityService {
    List<CityDto> retrieveAll();

    CityUpdateDto retrieveById(long id);

    long create(CityCreateDto cityCreateDto);

    void update(CityUpdateDto cityUpdateDto);

     void deleteById(long cityID, long deleteByID, LocalDateTime localDateTime);
}