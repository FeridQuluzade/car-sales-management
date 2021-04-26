package az.turbo.backend.currencies;

import az.turbo.backend.currencies.application.CurrencyService;
import az.turbo.backend.currencies.application.dto.CurrencyCreateDto;
import az.turbo.backend.shared.UserContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/currencies")
public class CurrencyController {
    private CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping(value = "/create")
    public long create(@Valid @RequestBody CurrencyCreateDto currencyCreateDto) {
        currencyCreateDto.setCreatedBy(UserContextHolder.getUserId());
        currencyCreateDto.setCreatedDate(LocalDateTime.now());
        return currencyService.create(currencyCreateDto);
    }
}
