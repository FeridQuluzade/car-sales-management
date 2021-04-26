package az.turbo.backend.currencies;

import az.turbo.backend.currencies.application.CurrencyService;
import az.turbo.backend.currencies.application.dto.CurrencyCreateDto;
import az.turbo.backend.currencies.application.dto.CurrencyDto;
import az.turbo.backend.shared.UserContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/currencies")
public class CurrencyController {
    private CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping(value = "/retrieve-all")
    @ResponseBody
    public List<CurrencyDto>retrieveAll(){
        return currencyService.retrieveAll();
    }

    @PostMapping(value = "/create")
    public long create(@Valid @RequestBody CurrencyCreateDto currencyCreateDto) {
        currencyCreateDto.setCreatedBy(UserContextHolder.getUserId());
        currencyCreateDto.setCreatedDate(LocalDateTime.now());
        return currencyService.create(currencyCreateDto);
    }
}
