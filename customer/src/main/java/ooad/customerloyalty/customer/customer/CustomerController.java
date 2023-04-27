package ooad.customerloyalty.customer.customer;

import ooad.customerloyalty.customer.customer.models.Auth;
import ooad.customerloyalty.customer.customer.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerFunctions customerFunctions = new CustomerFunctions(customerRepository);


    @Autowired
    private WebClient.Builder webClientBuilder;

    RestTemplate restTemplate = new RestTemplate();

    private String username;


//    @GetMapping("/")
//    @CrossOrigin
//    public String index()   {
//        return "index.html";
//    }


    @PostMapping("/login-auth")
    @CrossOrigin
    public boolean loginAuth(@RequestBody Auth auth)    {
        System.out.println();
        if(customerFunctions.checkAuth(auth))   {
            username = auth.getUsername();

//            webClientBuilder.build()
//                    .post()
//                    .uri("http://localhost:8081/rewards/set-user")
//                    .body(username, String.class);

            return true;
        }
        return false;
    }

    @PostMapping("/signup")
    @CrossOrigin
    public boolean signup(@RequestBody Customer customer)    {
        if(customerRepository.findCustomerByUsername(customer.getUsername()) == null)    {
            customerRepository.save(customer);
            return true;
        }
        return false;
    }

    @GetMapping("/logout")
    @CrossOrigin
    public String logout()    {
        username = null;
        return "Logged out";
    }

    @GetMapping("/getall")
    @CrossOrigin
    public List<Customer> getAll()  {
        return customerRepository.getAllCustomers();
    }

    @GetMapping("/profile")
    @CrossOrigin
    public Customer getProfile()    {
        // get profile from customer microservice
        return customerRepository.findCustomerByUsername(username);
    }

    @PostMapping("/transaction")
    @CrossOrigin
    public String getTransaction(@RequestBody int price)    {
        restTemplate.getForObject("http://localhost:8080/rewards/transaction", String.class, price);
        return "Transaction successful";
    }
}
