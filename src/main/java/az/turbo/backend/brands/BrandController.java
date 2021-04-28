package az.turbo.backend.brands;

import az.turbo.backend.brands.application.BrandService;
import az.turbo.backend.brands.application.dto.BrandCreateDto;
import az.turbo.backend.shared.UserContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/brands")
public class BrandController {
    private BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping(value = "/create")
    public long create(@Valid @RequestBody BrandCreateDto brandCreateDto) {
        brandCreateDto.setCreatedBy(UserContextHolder.getUserId());
        brandCreateDto.setCreatedDate(LocalDateTime.now());
        return brandService.create(brandCreateDto);
    }
}
