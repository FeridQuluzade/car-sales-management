package az.turbo.backend.colors;

import az.turbo.backend.colors.application.ColorService;
import az.turbo.backend.colors.application.dto.ColorCreateDto;
import az.turbo.backend.colors.application.dto.ColorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/colors")
public class ColorController {
    private ColorService colorService;

    @Autowired
    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping(value = "retrieve-all")
    @ResponseBody
    public List<ColorDto> retrieveAll() {
        return colorService.retrieveAll();
    }

    @PostMapping(value = "/create")
    public long create(@Valid @RequestBody ColorCreateDto colorCreateDto) {
        return colorService.create(colorCreateDto);
    }


}
