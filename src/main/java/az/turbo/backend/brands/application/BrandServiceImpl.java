package az.turbo.backend.brands.application;

import az.turbo.backend.brands.application.dto.BrandCreateDto;
import az.turbo.backend.brands.application.dto.BrandDto;
import az.turbo.backend.brands.domain.BrandRepository;
import az.turbo.backend.brands.domain.model.Brand;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private BrandRepository brandRepository;
    private ModelMapper modelMapper;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BrandDto> retrieveAll() {
        return brandRepository
                .findAll()
                .stream()
                .map(brand -> modelMapper.map(brand, BrandDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public long create(BrandCreateDto brandCreateDto) {
        Brand brand = modelMapper.map(brandCreateDto, Brand.class);
        return brandRepository.create(brand);
    }
}
