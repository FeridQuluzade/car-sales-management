package az.turbo.backend.customers.application;

import az.turbo.backend.customers.application.dto.CustomerCreateDto;
import az.turbo.backend.customers.application.dto.CustomerDto;
import az.turbo.backend.customers.domain.CustomerRepository;
import az.turbo.backend.customers.domain.model.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CustomerDto> retrieveAll() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> modelMapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public long create(CustomerCreateDto customerCreateDto) {
        Customer customer = modelMapper.map(customerCreateDto, Customer.class);
        return customerRepository.create(customer);
    }


}
