package az.turbo.backend.cities.application;

import az.turbo.backend.cities.application.dto.CityCreateDto;
import az.turbo.backend.cities.application.dto.CityDto;
import az.turbo.backend.cities.application.dto.CityUpdateDto;
import az.turbo.backend.cities.domain.model.City;

import java.util.List;

public interface CityServices {
    List<CityDto> retrieveAll();
    CityDto retrieveById(long id);
    long create(CityCreateDto cityCreateDto);
    void update(CityUpdateDto cityUpdateDto);
   // void deleteByID(long cityID, long deleteByID, LocalDateTime localDateTime);
}
