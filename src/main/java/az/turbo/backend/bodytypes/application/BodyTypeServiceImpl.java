package az.turbo.backend.bodytypes.application;

import az.turbo.backend.bodytypes.application.dto.BodyCreateDto;
import az.turbo.backend.bodytypes.application.dto.BodyDto;
import az.turbo.backend.bodytypes.application.dto.BodyTypeUpdateDto;
import az.turbo.backend.bodytypes.application.exception.BodyNotFoundException;
import az.turbo.backend.bodytypes.domain.BodyTypeRepository;
import az.turbo.backend.bodytypes.domain.model.BodyType;

import org.modelmapper.ModelMapper;


import java.util.List;

import java.util.stream.Collectors;

public class BodyTypeServiceImpl implements BodyTypeService {
    private BodyTypeRepository bodyTypeRepository;
    private ModelMapper modelMapper;


    public BodyTypeServiceImpl() {
        bodyTypeRepository = new BodyTypeRepository();
        modelMapper = new ModelMapper();
    }

    @Override
    public List<BodyDto> retrieveAll() {
        return bodyTypeRepository
                .findAll()
                .stream()
                .map(bodyType -> modelMapper.map(bodyType,BodyDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BodyDto retrieveById(long id)  {
        BodyType bodyType=bodyTypeRepository
                .findById(id)
                .orElseThrow(()->new BodyNotFoundException("BodyType not found! by Id"));
        return modelMapper.map(bodyType,BodyDto.class);
    }

    @Override
    public long create(BodyCreateDto bodyCreateDto) {
        BodyType bodyType = modelMapper.map(bodyCreateDto, BodyType.class);
        return bodyTypeRepository.create(bodyType);
    }

    @Override
    public void update(BodyTypeUpdateDto bodyTypeUpdateDto) {
        BodyType bodyType=modelMapper.map(bodyTypeUpdateDto,BodyType.class);
        bodyTypeRepository.update(bodyType);
    }

}
