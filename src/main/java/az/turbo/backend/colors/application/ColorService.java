package az.turbo.backend.colors.application;

import az.turbo.backend.colors.application.dto.ColorCreateDto;
import az.turbo.backend.colors.application.dto.ColorDto;

import java.util.List;

public interface ColorService {

    List<ColorDto> retrieveAll();

    long create(ColorCreateDto colorCreateDto);
}
