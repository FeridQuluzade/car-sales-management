package az.turbo.backend.brands.application;

import az.turbo.backend.brands.application.dto.BrandCreateDto;

public interface BrandService {

    long create(BrandCreateDto brandCreateDto);
}
