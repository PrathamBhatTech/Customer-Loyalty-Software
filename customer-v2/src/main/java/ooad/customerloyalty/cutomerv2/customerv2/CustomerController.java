package ooad.customerloyalty.cutomerv2.customerv2;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ooad.customerloyalty.cutomerv2.customerv2.models.Auth;
import ooad.customerloyalty.cutomerv2.customerv2.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    Logger logger = Logger.getLogger("transactions");
    FileHandler fh;
    Date date = new Date();

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerFunctions customerFunctions = new CustomerFunctions(customerRepository);


    private WebClient.Builder webClientBuilder;

    RestTemplate restTemplate = new RestTemplate();

    ObjectMapper mapper = new ObjectMapper();
    private String username;

    public CustomerController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;

        try    {
            fh = new FileHandler("customers.log", true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.info("Customer Controller Started");
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/login-auth")
    @CrossOrigin
    public String loginAuth(@RequestBody Auth auth)    {

        if(customerFunctions.checkAuth(auth))   {
            username = auth.getUsername();

            String message = "User " + username + " logged in.";
            logger.info(message);

            webClientBuilder.build()
                .post()
                .uri("http://localhost:8082/rewards/set-user")
                .body(Mono.just(username), String.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

            return "true";
        }

        String message = "Invalid login to user: " + auth.getUsername();
        logger.warning(message);

        return "false";
    }

    @PostMapping("/signup")
    @CrossOrigin
    public String signup(@RequestBody Customer customer)    {
        if(customerRepository.findCustomerByUsername(customer.getUsername()) == null)    {

            String message = "Account created with username: " + customer.getUsername();
            logger.info(message);

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

    @GetMapping("/get-logs")
    @CrossOrigin
    public List<String> getLogs() throws JsonProcessingException {
        List<String> logs = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("customers.log"));
            while (scanner.hasNextLine()) {
                logs.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return logs;
    }

}
