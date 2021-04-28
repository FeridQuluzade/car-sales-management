package az.turbo.backend.brands.application;

import az.turbo.backend.brands.application.dto.BrandCreateDto;
import az.turbo.backend.brands.application.dto.BrandDto;

import java.util.List;

public interface BrandService {
     List<BrandDto>retrieveAll();
    long create(BrandCreateDto brandCreateDto);
}
