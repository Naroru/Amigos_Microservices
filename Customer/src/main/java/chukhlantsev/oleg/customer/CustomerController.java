package chukhlantsev.oleg.customer;

import chukhlantsev.oleg.customer.dto.CustomerRegistrationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@Slf4j
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public void customerRegistration(@RequestBody CustomerRegistrationRequest customerRegistrationRequest)
    {
        log.info("New customer registration request {}", customerRegistrationRequest);
        customerService.registerCustomer(customerRegistrationRequest);
    }
}
