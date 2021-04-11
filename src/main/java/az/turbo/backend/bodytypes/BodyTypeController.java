package az.turbo.backend.bodytypes;

import az.turbo.backend.bodytypes.application.BodyTypeService;
import az.turbo.backend.bodytypes.application.dto.BodyTypeCreateDto;
import az.turbo.backend.bodytypes.application.dto.BodyTypeDto;
import az.turbo.backend.bodytypes.application.dto.BodyTypeUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/body-types")
public class BodyTypeController {
    private BodyTypeService bodyTypeService;

    @Autowired
    public BodyTypeController(BodyTypeService bodyTypeService) {
        this.bodyTypeService = bodyTypeService;
    }

    @GetMapping(value = "/retrieve-all")
    @ResponseBody
    public List<BodyTypeDto> retrieveAll() {
        return bodyTypeService.retrieveAll();
    }

    @GetMapping(value = "/retrieve-by-id/{id}")
    @ResponseBody
    public BodyTypeUpdateDto retrieveById(@PathVariable Long id) {
        return bodyTypeService.retrieveById(id);
    }

    @PostMapping(value = "/create")
    public long create(@Valid @RequestBody BodyTypeCreateDto bodyTypeCreateDto) {
        return bodyTypeService.create(bodyTypeCreateDto);
    }

    @PutMapping(value = "/update")
    public void update(@Valid @RequestBody BodyTypeUpdateDto bodyTypeUpdateDto) {
        bodyTypeService.update(bodyTypeUpdateDto);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable Long id) {
        bodyTypeService.deleteById(id, 1, LocalDateTime.now());
    }
}
