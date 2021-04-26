package az.turbo.backend.currencies.application;

import az.turbo.backend.currencies.application.dto.CurrencyCreateDto;
import az.turbo.backend.currencies.application.dto.CurrencyDto;
import az.turbo.backend.currencies.domain.CurrencyRepository;
import az.turbo.backend.currencies.domain.model.Currency;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private CurrencyRepository currencyRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository, ModelMapper modelMapper) {
        this.currencyRepository = currencyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CurrencyDto> retrieveAll() {
        return currencyRepository
                .findAll()
                .stream()
                .map(currency -> modelMapper.map(currency,CurrencyDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public long create(CurrencyCreateDto currencyCreateDto) {
        Currency currency = modelMapper.map(currencyCreateDto, Currency.class);
        return currencyRepository.create(currency);
    }
}
