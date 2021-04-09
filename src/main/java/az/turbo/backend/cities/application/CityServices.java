package az.turbo.backend.cities.application;

import az.turbo.backend.cities.application.dto.CityCreateDto;
import az.turbo.backend.cities.application.dto.CityDto;

import java.util.List;

public interface CityServices {
    List<CityDto> retrieveAll();
   // City retrieveById(long id);
    long create(CityCreateDto cityCreateDto);
   // long update(CityUpdateDto cityUpdateDto);
   // void deleteByID(long cityID, long deleteByID, LocalDateTime localDateTime);
}
