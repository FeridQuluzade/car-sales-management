package az.turbo.backend.supplies.application;

import az.turbo.backend.supplies.application.dto.SupplyCreateDto;

public interface SupplyService {

    long create(SupplyCreateDto supplyCreateDto);
}
