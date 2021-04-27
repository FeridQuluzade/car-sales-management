package az.turbo.backend.currencies.application;

import az.turbo.backend.currencies.application.dto.CurrencyCreateDto;
import az.turbo.backend.currencies.application.dto.CurrencyDto;
import az.turbo.backend.currencies.application.dto.CurrencyUpdateDto;

import java.time.LocalDateTime;
import java.util.List;

public interface CurrencyService {
    List<CurrencyDto> retrieveAll();

    long create(CurrencyCreateDto currencyCreateDto);

    void update(CurrencyUpdateDto currencyUpdateDto);

    void deleteById(long id,long deleteBy,LocalDateTime deletedDate);
}
