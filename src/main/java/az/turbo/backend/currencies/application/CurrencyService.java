package az.turbo.backend.currencies.application;

import az.turbo.backend.currencies.application.dto.CurrencyCreateDto;

public interface CurrencyService {

    long create(CurrencyCreateDto currencyCreateDto);
}
