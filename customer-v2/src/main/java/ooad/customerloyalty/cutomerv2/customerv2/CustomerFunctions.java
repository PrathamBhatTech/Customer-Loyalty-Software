package ooad.customerloyalty.cutomerv2.customerv2;

import ooad.customerloyalty.cutomerv2.customerv2.models.Auth;
import ooad.customerloyalty.cutomerv2.customerv2.models.Customer;
import org.springframework.stereotype.Component;

import java.beans.JavaBean;

@Component
public class CustomerFunctions  {

    private final CustomerRepository customerRepository;

    public CustomerFunctions(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public boolean checkAuth(Auth auth) {
        Customer customer = customerRepository.findCustomerByUsername(auth.getUsername());
        if (customer == null) {
            return false;
        }
        return customer.getPassword().equals(auth.getPassword());
    }
}
