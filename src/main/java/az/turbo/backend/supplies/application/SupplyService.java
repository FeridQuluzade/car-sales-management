package az.turbo.backend.supplies.application;

import az.turbo.backend.supplies.application.dto.SupplyCreateDto;
import az.turbo.backend.supplies.application.dto.SupplyDto;
import az.turbo.backend.supplies.application.dto.SupplyUpdateDto;

import java.util.List;

public interface SupplyService {

    List<SupplyDto> retrieveAll();

    SupplyUpdateDto retrieveById(long id);

    long create(SupplyCreateDto supplyCreateDto);

    void update(SupplyUpdateDto supplyUpdateDto);
}
