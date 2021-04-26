package az.turbo.backend.currencies.application;

import az.turbo.backend.currencies.application.dto.CurrencyCreateDto;
import az.turbo.backend.currencies.application.dto.CurrencyDto;

import java.util.List;

public interface CurrencyService {
    List<CurrencyDto> retrieveAll();

    long create(CurrencyCreateDto currencyCreateDto);
}
