package az.turbo.backend.bodytypes.application;

import az.turbo.backend.bodytypes.application.dto.BodyCreateDto;
import az.turbo.backend.bodytypes.application.dto.BodyDto;
import az.turbo.backend.bodytypes.application.dto.BodyUptadeDto;

import java.time.LocalDateTime;
import java.util.List;

public interface BodyTypeService {


    long create(BodyCreateDto bodyCreateDto);

}
