package az.turbo.backend.bodytypes;

import az.turbo.backend.bodytypes.application.BodyTypeService;
import az.turbo.backend.bodytypes.application.dto.BodyTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
