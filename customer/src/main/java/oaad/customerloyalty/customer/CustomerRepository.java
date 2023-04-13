@Repository
@Qualifier("customerRepository")
public class CustomerRepository {

    @Query("username:'?0'}")
    Customer findCustomerByUsername(String username);

}
