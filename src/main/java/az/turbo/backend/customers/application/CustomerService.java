package az.turbo.backend.customers.application;

import az.turbo.backend.customers.application.dto.CustomerCreateDto;
import az.turbo.backend.customers.application.dto.CustomerDto;
import az.turbo.backend.customers.application.dto.CustomerUpdateDto;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomerService {
    List<CustomerDto> retrieveAll();

    CustomerUpdateDto retrieveById(long id);

    long create(CustomerCreateDto customerCreateDto);

    void update(CustomerUpdateDto customerUpdateDto);

    void deleteById(long customerID, long deleteByID, LocalDateTime localDateTime);
}
