package hse.kpo.services;

import hse.kpo.domains.Customer;
import hse.kpo.interfaces.InterfaceCustomerProvider;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * List of customers.
 */
@Getter
@Component
public class CustomerStorage implements InterfaceCustomerProvider {
    private final List<Customer> customers = new ArrayList<>();


    public void addCustomer(Customer customer) {
        customers.add(customer); // просто добавляем покупателя в список
    }
}
