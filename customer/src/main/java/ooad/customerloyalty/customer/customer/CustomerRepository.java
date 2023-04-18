package ooad.customerloyalty.customer.customer;

import ooad.customerloyalty.customer.customer.models.Customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("customerRepository")
public interface CustomerRepository extends MongoRepository<Customer, String> {
    @Query("{username:'?0'}")
    Customer findCustomerByUsername(String username);

    @Query("{}")
    List<Customer> getAllCustomers();

}
