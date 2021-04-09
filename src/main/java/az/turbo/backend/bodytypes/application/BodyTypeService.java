package az.turbo.backend.bodytypes.application;

import az.turbo.backend.bodytypes.application.dto.BodyCreateDto;
import az.turbo.backend.bodytypes.application.dto.BodyDto;
import az.turbo.backend.bodytypes.application.exception.BodyNotFoundException;


import java.time.LocalDateTime;
import java.util.List;

public interface BodyTypeService {
    List<BodyDto> retrieveAll();

    BodyDto retrieveById(long id) throws BodyNotFoundException;

    long create(BodyCreateDto bodyCreateDto);

}
