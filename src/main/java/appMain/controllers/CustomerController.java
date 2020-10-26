package appMain.controllers;

import appMain.entities.Customer;
import appMain.entities.dto.CustomersDTO;
import appMain.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public @ResponseBody
    CustomersDTO getAllCustomers(){
        CustomersDTO customersDTO = new CustomersDTO();
        customersDTO.setCustomers(customerService.getAllCustomers());
        return customersDTO;
    }

    @PostMapping
    public void saveCustomer(@RequestBody Customer customer){
        customer.setRegistrationDate(new Date(System.currentTimeMillis()));
        customerService.saveCustomer(customer);
    }
}
