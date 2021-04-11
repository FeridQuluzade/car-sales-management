package az.turbo.backend.colors.application;

import az.turbo.backend.colors.application.dto.ColorCreateDto;
import az.turbo.backend.colors.domain.ColorRepository;
import az.turbo.backend.colors.domain.model.Color;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColorServiceImpl implements ColorService {

    private ColorRepository colorRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ColorServiceImpl(ColorRepository colorRepository,ModelMapper modelMapper) {
        this.colorRepository=colorRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public long create(ColorCreateDto colorCreateDto) {
        Color color = modelMapper.map(colorCreateDto, Color.class);
        return colorRepository.create(color);
    }
}
