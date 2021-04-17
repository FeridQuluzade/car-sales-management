package az.turbo.backend.engineVolumes.application;

import az.turbo.backend.engineVolumes.application.dto.EngineVolumeCreateDto;

public interface EngineVolumeService {

    long create(EngineVolumeCreateDto engineVolumeCreateDto);
}
