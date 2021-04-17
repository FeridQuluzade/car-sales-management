package az.turbo.backend.engineVolumes.application;

import az.turbo.backend.engineVolumes.application.dto.EngineVolumeCreateDto;
import az.turbo.backend.engineVolumes.application.dto.EngineVolumeDto;
import az.turbo.backend.engineVolumes.application.dto.EngineVolumeUpdateDto;
import az.turbo.backend.engineVolumes.application.exception.EngineVolumeNotFoundException;
import az.turbo.backend.engineVolumes.domain.EngineVolumeRepository;
import az.turbo.backend.engineVolumes.domain.model.EngineVolume;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EngineVolumeServiceImpl implements EngineVolumeService {
    private EngineVolumeRepository engineVolumeRepository;
    private ModelMapper modelMapper;

    @Autowired
    public EngineVolumeServiceImpl(EngineVolumeRepository engineVolumeRepository, ModelMapper modelMapper) {
        this.engineVolumeRepository = engineVolumeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<EngineVolumeDto> retrieveAll() {
        return engineVolumeRepository
                .findAll()
                .stream()
                .map(engineVolume -> modelMapper.map(engineVolume, EngineVolumeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public EngineVolumeUpdateDto retrieveById(long id) {
        EngineVolume engineVolume = engineVolumeRepository
                .findById(id)
                .orElseThrow(() -> new EngineVolumeNotFoundException("Engine-volume not found!"));

        return modelMapper.map(engineVolume, EngineVolumeUpdateDto.class);
    }

    @Override
    public long create(EngineVolumeCreateDto engineVolumeCreateDto) {
        EngineVolume engineVolume = modelMapper.map(engineVolumeCreateDto, EngineVolume.class);
        return engineVolumeRepository.create(engineVolume);
    }
}
