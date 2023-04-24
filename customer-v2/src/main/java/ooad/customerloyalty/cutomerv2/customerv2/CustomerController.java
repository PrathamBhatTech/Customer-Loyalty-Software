package ooad.customerloyalty.cutomerv2.customerv2;


import ooad.customerloyalty.cutomerv2.customerv2.models.Auth;
import ooad.customerloyalty.cutomerv2.customerv2.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerFunctions customerFunctions = new CustomerFunctions(customerRepository);


    private WebClient.Builder webClientBuilder;

    RestTemplate restTemplate = new RestTemplate();


    private String username;

    public CustomerController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @PostMapping("/login-auth")
    @CrossOrigin
    public String loginAuth(@RequestBody Auth auth)    {
//        System.out.println();
        if(customerFunctions.checkAuth(auth))   {
            username = auth.getUsername();

            webClientBuilder.build()
                .post()
                .uri("http://localhost:8082/rewards/set-user")
                .body(Mono.just(username), String.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

            return "true";
        }
        return "false";
    }

    @PostMapping("/signup")
    @CrossOrigin
    public String signup(@RequestBody Customer customer)    {
        if(customerRepository.findCustomerByUsername(customer.getUsername()) == null)    {
            customerRepository.save(customer);

            // add user to points collection
            webClientBuilder.build()
                .post()
                .uri("http://localhost:8082/rewards/new-user")
                .body(Mono.just(customer.getUsername()), String.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

            return "true";
        }
        return "false";
    }

    @GetMapping("/logout")
    @CrossOrigin
    public boolean logout()    {
        username = null;
        return true;
    }

    @GetMapping("/getall")
    public List<Customer> getAll()  {
        return customerRepository.getAllCustomers();
    }

    @GetMapping("/profile")
    @CrossOrigin
    public Customer getProfile()    {
        // get profile from customer microservice
        return customerRepository.findCustomerByUsername(username);
    }

    @PostMapping("/edit-profile")
    @CrossOrigin
    public Customer editProfile(@RequestBody Customer tmp_customer)    {
        // edit profile in customer microservice;
        Customer customer = customerRepository.findCustomerByUsername(tmp_customer.getUsername());

        customer.setName(tmp_customer.getName());
        customer.setPassword(tmp_customer.getPassword());
        customer.setPhoneNumber(tmp_customer.getPhoneNumber());
        customer.setDob(tmp_customer.getDob());

        return customerRepository.save(customer);
    }
}
