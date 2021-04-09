package az.turbo.backend.bodytypes.application;

import az.turbo.backend.bodytypes.application.dto.BodyCreateDto;
import az.turbo.backend.bodytypes.application.dto.BodyDto;
import az.turbo.backend.bodytypes.application.dto.BodyTypeUpdateDto;

import java.time.LocalDateTime;
import java.util.List;

public interface BodyTypeService {
    List<BodyDto> retrieveAll();

    BodyDto retrieveById(long id);

    long create(BodyCreateDto bodyCreateDto);

    void update(BodyTypeUpdateDto bodyTypeUpdateDto);

    void deleteById(long id, long deletedBy, LocalDateTime deletedDate);
}
