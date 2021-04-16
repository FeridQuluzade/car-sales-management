package az.turbo.backend.customers;

import az.turbo.backend.customers.application.CustomerService;
import az.turbo.backend.customers.application.dto.CustomerCreateDto;
import az.turbo.backend.customers.application.dto.CustomerDto;
import az.turbo.backend.customers.application.dto.CustomerUpdateDto;
import az.turbo.backend.customers.domain.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/retrieve-all")
    @ResponseBody
    public List<CustomerDto> retrieveAll() {
        return customerService.retrieveAll();
    }

    @GetMapping(value = "/retrieve-by-id")
    @ResponseBody
    public CustomerUpdateDto retrieveById(@PathVariable long id){
        return customerService.retrieveById(id);
    }

    @PostMapping(value = "/create")
    public long create(@Valid @RequestBody CustomerCreateDto customerCreateDto){
        return customerService.create(customerCreateDto);
    }


}
