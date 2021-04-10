package az.turbo.backend.cities.application;

import az.turbo.backend.cities.application.dto.CityCreateDto;
import az.turbo.backend.cities.application.dto.CityDto;
import az.turbo.backend.cities.application.dto.CityUpdateDto;
import az.turbo.backend.cities.application.exception.CityNotFoundException;
import az.turbo.backend.cities.domain.CityRepository;
import az.turbo.backend.cities.domain.model.City;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CityServiceImpl implements CityService {
    private CityRepository cityRepository;
    private ModelMapper modelMapper;

    public CityServiceImpl() {
        cityRepository = new CityRepository();
        modelMapper = new ModelMapper();
    }

    @Override
    public List<CityDto> retrieveAll() {
        return cityRepository.findAll()
                .stream()
                .map(city -> modelMapper.map(city, CityDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CityDto retrieveById(long id) {
        City city = cityRepository
                .findById(id)
                .orElseThrow(()-> new CityNotFoundException("City Not Found by this ID = "+id));
            return modelMapper.map(city, CityDto.class);
    }

    @Override
    public long create(CityCreateDto cityCreateDto) {
        City city=modelMapper.map(cityCreateDto,City.class);
        return cityRepository.create(city);
    }

    @Override
    public void update(CityUpdateDto cityUpdateDto) {
        City city = modelMapper.map(cityUpdateDto,City.class);
        cityRepository.update(city);
    }
}