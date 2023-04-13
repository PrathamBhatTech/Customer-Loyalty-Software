import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CustomerController {

    @Autowired
    private CatalogService catalogService;

    @PostMapping("/login")
    public boolean login(@RequestBody Customer customer)    {
        return catalogService.checkAuth(customer.getUsername(), customer.getPassword());
    }
}
