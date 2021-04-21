package az.turbo.backend.engineVolumes;

import az.turbo.backend.engineVolumes.application.EngineVolumeService;
import az.turbo.backend.engineVolumes.application.dto.EngineVolumeCreateDto;
import az.turbo.backend.engineVolumes.application.dto.EngineVolumeDto;
import az.turbo.backend.engineVolumes.application.dto.EngineVolumeUpdateDto;
import az.turbo.backend.shared.UserContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/engine-volumes")
public class EngineVolumeController {
    private EngineVolumeService engineVolumeService;

    @Autowired
    public EngineVolumeController(EngineVolumeService engineVolumeService) {
        this.engineVolumeService = engineVolumeService;
    }

    @GetMapping(value = "/retrieve-all")
    @ResponseBody
    public List<EngineVolumeDto> retrieveAll() {
        return engineVolumeService.retrieveAll();
    }

    @GetMapping(value = "/retrieve-by-id/{id}")
    @ResponseBody
    public EngineVolumeUpdateDto retrieveById(@PathVariable Long id) {
        return engineVolumeService.retrieveById(id);
    }

    @PostMapping(value = "/create")
    public long create(@Valid @RequestBody EngineVolumeCreateDto engineVolumeCreateDto) {
        engineVolumeCreateDto.setCreatedBy(UserContextHolder.getUserId());
        engineVolumeCreateDto.setCreatedDate(LocalDateTime.now());
        return engineVolumeService.create(engineVolumeCreateDto);
    }

    @PutMapping(value = "/update")
    public void update(@Valid @RequestBody EngineVolumeUpdateDto engineVolumeUpdateDto) {
        engineVolumeUpdateDto.setUpdatedBy(UserContextHolder.getUserId());
        engineVolumeUpdateDto.setUpdatedDate(LocalDateTime.now());
        engineVolumeService.update(engineVolumeUpdateDto);
    }

    @DeleteMapping(value = "/deleted/{id}")
    public void delete(@PathVariable Long id) {
        engineVolumeService.deleteById(id, UserContextHolder.getUserId(), LocalDateTime.now());
    }
}
