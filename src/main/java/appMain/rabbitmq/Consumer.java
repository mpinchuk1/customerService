package appMain.rabbitmq;


import appMain.entities.Customer;
import appMain.services.CustomerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class Consumer {

    private final CustomerService customerService;

    @Autowired
    public Consumer(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void consume(Customer customer) {
        customer.setRegistrationDate(new Date(System.currentTimeMillis()));
        customerService.saveCustomer(customer);
    }
}