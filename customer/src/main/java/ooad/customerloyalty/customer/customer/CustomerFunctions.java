package ooad.customerloyalty.customer.customer;

import ooad.customerloyalty.customer.customer.models.Auth;
import ooad.customerloyalty.customer.customer.models.Customer;
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
