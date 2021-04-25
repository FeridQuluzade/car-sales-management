package az.turbo.backend.cities;

import az.turbo.backend.cities.application.CityService;
import az.turbo.backend.cities.application.dto.CityCreateDto;
import az.turbo.backend.cities.application.dto.CityDto;
import az.turbo.backend.cities.application.dto.CityUpdateDto;
import az.turbo.backend.shared.UserContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/cities")
public class CityController {
    private CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping(value = "/retrieve-all")
    @ResponseBody
    public List<CityDto> retrieveAll() {
        return cityService.retrieveAll();
    }

    @GetMapping(value = "/retrieve-by-id")
    @ResponseBody
    public CityUpdateDto retrieveById(@PathVariable Long id) {
        return cityService.retrieveById(id);
    }

    @PostMapping(value = "/create")
    public long create(@Valid @RequestBody CityCreateDto cityCreateDto) {
        cityCreateDto.setCreatedBy(UserContextHolder.getUserId());
        cityCreateDto.setCreatedDate(LocalDateTime.now());
        return cityService.create(cityCreateDto);
    }

    @PutMapping(value = "/update")
    public void update(@Valid @RequestBody CityUpdateDto cityUpdateDto) {
        cityUpdateDto.setUpdatedBy(UserContextHolder.getUserId());
        cityUpdateDto.setUpdatedDate(LocalDateTime.now());
        cityService.update(cityUpdateDto);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable Long id) {
        cityService.deleteById(id, UserContextHolder.getUserId(), LocalDateTime.now());
    }


}
