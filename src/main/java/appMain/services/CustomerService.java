package appMain.services;


import appMain.entities.Customer;
import appMain.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @Transactional
    public void saveCustomer(Customer customer){
        customerRepository.save(customer);
    }
}
