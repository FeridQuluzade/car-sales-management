package az.turbo.backend.bodytypes.application;

import az.turbo.backend.bodytypes.application.dto.BodyTypeCreateDto;
import az.turbo.backend.bodytypes.application.dto.BodyTypeDto;


import java.util.List;

public interface BodyTypeService {
    List<BodyTypeDto>retrieveAll() ;

    long create(BodyTypeCreateDto bodyTypeCreateDto);
}