package hse.kpo.services;

import hse.kpo.domains.Customer;
import hse.kpo.interfaces.InterfaceCustomerProvider;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * List of customers.
 */
@Component
public class CustomerStorage implements InterfaceCustomerProvider {
    private List<Customer> customers = new ArrayList<>();

    @Override
    public List<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer); // просто добавляем покупателя в список
    }
}
