package appMain.controllers.grpc;

import appMain.entities.Customer;
import appMain.services.CustomerService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.appMain.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@GrpcService
public class CustomerServiceImpl extends customerServiceGrpc.customerServiceImplBase {
    private final CustomerService customerService;

    @Autowired
    public CustomerServiceImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void get(GetCustomerRequest request, StreamObserver<GetCustomerResponse> responseStreamObserver) {
        List<Customer> customers = customerService.getAllCustomers();

        List<ProtoCustomer> protoCustomers = new ArrayList<>();
        for (Customer customer : customers) {
            ProtoCustomer protoCustomer = ProtoCustomer.newBuilder()
                    .setFirstName(customer.getFirstName())
                    .setLastName(customer.getLastName())
                    .setAge(customer.getAge())
                    .build();
            protoCustomers.add(protoCustomer);
        }
        GetCustomerResponse response = GetCustomerResponse.newBuilder().addAllCustomers(protoCustomers).build();
        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }

    @Override
    public void create(CreateCustomerRequest request, StreamObserver<CreateCustomerResponse> responseStreamObserver) {
        ProtoCustomer protoCustomer = request.getCustomer();
        Customer customer = new Customer(UUID.fromString(protoCustomer.getId()), protoCustomer.getFirstName(),
                protoCustomer.getLastName(), protoCustomer.getAge());

        customerService.saveCustomer(customer);
        CreateCustomerResponse response = CreateCustomerResponse.newBuilder().build();
        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }
}
