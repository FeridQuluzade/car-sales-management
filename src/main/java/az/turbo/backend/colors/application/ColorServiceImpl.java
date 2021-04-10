package az.turbo.backend.colors.application;

import az.turbo.backend.colors.application.dto.ColorCreateDto;
import az.turbo.backend.colors.domain.ColorRepository;
import az.turbo.backend.colors.domain.model.Color;
import org.modelmapper.ModelMapper;

public class ColorServiceImpl implements ColorService {

    private ColorRepository colorRepository;
    private ModelMapper modelMapper;

    public ColorServiceImpl() {
        colorRepository = new ColorRepository();
        modelMapper = new ModelMapper();
    }

    @Override
    public long create(ColorCreateDto colorCreateDto) {
        Color color = modelMapper.map(colorCreateDto, Color.class);
        return colorRepository.create(color);
    }
}
