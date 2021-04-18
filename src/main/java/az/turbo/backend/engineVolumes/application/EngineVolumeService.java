package az.turbo.backend.engineVolumes.application;

import az.turbo.backend.engineVolumes.application.dto.EngineVolumeCreateDto;
import az.turbo.backend.engineVolumes.application.dto.EngineVolumeDto;
import az.turbo.backend.engineVolumes.application.dto.EngineVolumeUpdateDto;

import java.time.LocalDateTime;
import java.util.List;

public interface EngineVolumeService {
    List<EngineVolumeDto> retrieveAll();

    EngineVolumeUpdateDto retrieveById(long id);

    long create(EngineVolumeCreateDto engineVolumeCreateDto);

    void update(EngineVolumeUpdateDto engineVolumeUpdateDto);

    void deleteById(long id , long deleteBy, LocalDateTime deletedDate);
}
