package az.turbo.backend.bodytypes.application;

import az.turbo.backend.bodytypes.application.dto.BodyCreateDto;
import az.turbo.backend.bodytypes.application.dto.BodyDto;


import java.time.LocalDateTime;
import java.util.List;

public interface BodyTypeService {
    List<BodyDto>retrieveAll() ;
    long create(BodyCreateDto bodyCreateDto);

}
