package az.turbo.backend.customers;

import az.turbo.backend.customers.application.CustomerService;
import az.turbo.backend.customers.application.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

}
