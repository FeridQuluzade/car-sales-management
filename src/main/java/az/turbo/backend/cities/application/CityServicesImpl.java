package az.turbo.backend.cities.application;

import az.turbo.backend.cities.application.dto.CityCreateDto;
import az.turbo.backend.cities.application.dto.CityDto;
import az.turbo.backend.cities.domain.CityRepository;
import az.turbo.backend.cities.domain.model.City;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CityServicesImpl implements CityServices {
    private CityRepository cityRepository;
    private ModelMapper modelMapper;

    public CityServicesImpl() {
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
    public long create(CityCreateDto cityCreateDto) {
        City city=modelMapper.map(cityCreateDto,City.class);
        return cityRepository.create(city);
    }


}
