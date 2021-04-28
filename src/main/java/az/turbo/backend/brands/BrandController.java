package az.turbo.backend.brands;

import az.turbo.backend.brands.application.BrandService;
import az.turbo.backend.brands.application.dto.BrandCreateDto;
import az.turbo.backend.brands.application.dto.BrandDto;
import az.turbo.backend.shared.UserContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/brands")
public class BrandController {
    private BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping(value = "/retrieve-all")
    @ResponseBody
    public List<BrandDto> retrieveAll(){
        return brandService.retrieveAll();
    }

    @PostMapping(value = "/create")
    public long create(@Valid @RequestBody BrandCreateDto brandCreateDto) {
        brandCreateDto.setCreatedBy(UserContextHolder.getUserId());
        brandCreateDto.setCreatedDate(LocalDateTime.now());
        return brandService.create(brandCreateDto);
    }
}
