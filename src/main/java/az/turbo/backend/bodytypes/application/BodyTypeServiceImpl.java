package az.turbo.backend.bodytypes.application;

import az.turbo.backend.bodytypes.application.dto.BodyTypeCreateDto;
import az.turbo.backend.bodytypes.application.dto.BodyTypeDto;
import az.turbo.backend.bodytypes.application.dto.BodyTypeUpdateDto;
import az.turbo.backend.bodytypes.application.exception.BodyTypeNotFoundException;
import az.turbo.backend.bodytypes.domain.BodyTypeRepository;
import az.turbo.backend.bodytypes.domain.model.BodyType;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class BodyTypeServiceImpl implements BodyTypeService {
    private BodyTypeRepository bodyTypeRepository;
    private ModelMapper modelMapper;

    @Autowired
    public BodyTypeServiceImpl(BodyTypeRepository bodyTypeRepository, ModelMapper modelMapper) {
        this.bodyTypeRepository = bodyTypeRepository;
        this.modelMapper = modelMapper;
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
    public BodyTypeUpdateDto retrieveById(long id) {
        BodyType bodyType = bodyTypeRepository
                .findById(id)
                .orElseThrow(() -> new BodyTypeNotFoundException("BodyType not found! by Id"));

        return modelMapper.map(bodyType, BodyTypeUpdateDto.class);
    }

    @Override
    public long create(BodyTypeCreateDto bodyTypeCreateDto) {
        BodyType bodyType = modelMapper.map(bodyTypeCreateDto, BodyType.class);
        return bodyTypeRepository.create(bodyType);
    }

    @Override
    public void update(BodyTypeUpdateDto bodyTypeUpdateDto) {
        BodyType bodyType = modelMapper.map(bodyTypeUpdateDto, BodyType.class);
        bodyTypeRepository.update(bodyType);
    }

    @Override
    public void deleteById(long id, long deletedBy, LocalDateTime deletedDate) {
        bodyTypeRepository.DeleteById(id, deletedBy, deletedDate);
    }
}