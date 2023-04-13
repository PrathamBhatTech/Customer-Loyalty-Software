
public class CustomerFunctions  {
    public boolean checkAuth(String username, String password) {
        Customer customer = customerRepository.findCustomerByUsername(username);
        if (customer == null) {
            return false;
        }
        if (customer.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
