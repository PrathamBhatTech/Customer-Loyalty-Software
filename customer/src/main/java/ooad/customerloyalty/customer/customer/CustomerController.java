package ooad.customerloyalty.customer.customer;

import ooad.customerloyalty.customer.customer.models.Auth;
import ooad.customerloyalty.customer.customer.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerFunctions customerFunctions = new CustomerFunctions(customerRepository);

    private String username;


    @PostMapping("/login")
    public boolean login(@RequestBody Auth auth)    {
        if(customerFunctions.checkAuth(auth))   {
            username = auth.getUsername();
            return true;
        }
        return false;
    }

    @PostMapping("/signup")
    public boolean signup(@RequestBody Customer customer)    {
        if(customerRepository.findCustomerByUsername(customer.getUsername()) == null)    {
            customerRepository.save(customer);
            return true;
        }
        return false;
    }

    @GetMapping("/logout")
    public String logout()    {
        username = null;
        return "Logged out";
    }

    @GetMapping("/getall")
    public List<Customer> getAll()  {
        return customerRepository.getAllCustomers();
    }

    @GetMapping("/profile")
    public Customer getProfile()    {
        // get profile from customer microservice
        return customerRepository.findCustomerByUsername(username);
    }
}
