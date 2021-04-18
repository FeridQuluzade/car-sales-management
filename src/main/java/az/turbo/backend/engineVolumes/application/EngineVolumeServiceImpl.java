package az.turbo.backend.engineVolumes.application;

import az.turbo.backend.engineVolumes.application.dto.EngineVolumeCreateDto;
import az.turbo.backend.engineVolumes.domain.EngineVolumeRepository;
import az.turbo.backend.engineVolumes.domain.model.EngineVolume;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public long create(EngineVolumeCreateDto engineVolumeCreateDto) {
        EngineVolume engineVolume = modelMapper.map(engineVolumeCreateDto, EngineVolume.class);
        return engineVolumeRepository.create(engineVolume);
    }
}
