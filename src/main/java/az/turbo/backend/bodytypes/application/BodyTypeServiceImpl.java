package az.turbo.backend.bodytypes.application;

import az.turbo.backend.bodytypes.application.dto.BodyTypeCreateDto;
import az.turbo.backend.bodytypes.application.dto.BodyTypeDto;
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
    public List<BodyTypeDto> retrieveAll() {
        return bodyTypeRepository
                .findAll()
                .stream()
                .map(bodyType -> modelMapper.map(bodyType, BodyTypeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public long create(BodyTypeCreateDto bodyTypeCreateDto) {
        BodyType bodyType = modelMapper.map(bodyTypeCreateDto, BodyType.class);
        return bodyTypeRepository.create(bodyType);
    }
}