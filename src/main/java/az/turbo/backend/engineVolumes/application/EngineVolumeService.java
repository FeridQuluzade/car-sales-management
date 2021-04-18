package az.turbo.backend.engineVolumes.application;

import az.turbo.backend.engineVolumes.application.dto.EngineVolumeCreateDto;
import az.turbo.backend.engineVolumes.application.dto.EngineVolumeDto;

import java.util.List;

public interface EngineVolumeService {
    List<EngineVolumeDto> retrieveAll();

    long create(EngineVolumeCreateDto engineVolumeCreateDto);

}
