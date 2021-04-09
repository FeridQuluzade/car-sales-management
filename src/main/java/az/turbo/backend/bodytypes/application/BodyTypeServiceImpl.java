package az.turbo.backend.bodytypes.application;

import az.turbo.backend.bodytypes.application.dto.BodyCreateDto;
import az.turbo.backend.bodytypes.application.dto.BodyDto;
import az.turbo.backend.bodytypes.application.dto.BodyUptadeDto;

import az.turbo.backend.bodytypes.domain.BodyTypeRepository;
import az.turbo.backend.bodytypes.domain.model.BodyType;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BodyTypeServiceImpl implements BodyTypeService {
    private BodyTypeRepository bodyTypeRepository;
    private ModelMapper modelMapper;

    public BodyTypeServiceImpl() {
        bodyTypeRepository = new BodyTypeRepository();
        modelMapper = new ModelMapper();
    }

    @Override
    public long create(BodyCreateDto bodyCreateDto) {
        BodyType bodyType = modelMapper.map(bodyCreateDto, BodyType.class);
        return bodyTypeRepository.create(bodyType);
    }

}
