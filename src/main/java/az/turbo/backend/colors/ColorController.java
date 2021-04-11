package az.turbo.backend.colors;

import az.turbo.backend.colors.application.ColorService;
import az.turbo.backend.colors.application.dto.ColorCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/colors")
public class ColorController {
    private ColorService colorService;

    @Autowired
    public ColorController(ColorService colorService){ this.colorService=colorService; }

    @PostMapping(value = "/create")
    public  long create(@Valid @RequestBody ColorCreateDto colorCreateDto){
        return colorService.create(colorCreateDto);
    }
}
