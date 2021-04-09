package az.turbo.backend.bodytypes.application;

import az.turbo.backend.bodytypes.application.dto.BodyCreateDto;

public interface BodyTypeService {


    long create(BodyCreateDto bodyCreateDto);

}
