package az.turbo.backend.engineVolumes;

import az.turbo.backend.engineVolumes.application.EngineVolumeService;
import az.turbo.backend.engineVolumes.application.dto.EngineVolumeCreateDto;
import az.turbo.backend.engineVolumes.application.dto.EngineVolumeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/engine-volumes")
public class EngineVolumeController {
    private EngineVolumeService engineVolumeService;

    @Autowired
    public EngineVolumeController(EngineVolumeService engineVolumeService){
        this.engineVolumeService=engineVolumeService;
    }

    @GetMapping(value = "/retrieve-all")
    @ResponseBody
    public List<EngineVolumeDto> retrieveAll(){
        return engineVolumeService.retrieveAll();
    }

    @PostMapping(value = "/create")
    public long create(@Valid @RequestBody EngineVolumeCreateDto engineVolumeCreateDto){
       return engineVolumeService.create(engineVolumeCreateDto);
    }
}
