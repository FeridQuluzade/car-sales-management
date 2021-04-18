package az.turbo.backend.engineVolumes;

import az.turbo.backend.engineVolumes.application.EngineVolumeService;
import az.turbo.backend.engineVolumes.application.dto.EngineVolumeCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/engine-volumes")
public class EngineVolumeController {
    private EngineVolumeService engineVolumeService;

    @Autowired
    public EngineVolumeController(EngineVolumeService engineVolumeService){
        this.engineVolumeService=engineVolumeService;
    }

    @PostMapping(value = "/create")
    public long create(@Valid @RequestBody EngineVolumeCreateDto engineVolumeCreateDto){
       return engineVolumeService.create(engineVolumeCreateDto);
    }
}
