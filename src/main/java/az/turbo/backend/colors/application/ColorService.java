package az.turbo.backend.colors.application;

import az.turbo.backend.colors.application.dto.ColorCreateDto;

public interface ColorService {

    long create(ColorCreateDto colorCreateDto);
}
