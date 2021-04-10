package az.turbo.backend.bodytypes.application;

import az.turbo.backend.bodytypes.application.dto.BodyTypeCreateDto;
import az.turbo.backend.bodytypes.application.dto.BodyTypeDto;
import az.turbo.backend.bodytypes.application.dto.BodyTypeUpdateDto;


import java.time.LocalDateTime;
import java.util.List;

public interface BodyTypeService {
    List<BodyTypeDto>retrieveAll() ;

    BodyTypeUpdateDto retrieveById(long id);

    long create(BodyTypeCreateDto bodyTypeCreateDto);

    void update(BodyTypeUpdateDto bodyTypeUpdateDto);

    void deleteById(long id, long deletedBy, LocalDateTime deletedDate);
}