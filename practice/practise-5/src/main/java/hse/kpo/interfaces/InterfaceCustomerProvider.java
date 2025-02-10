package hse.kpo.interfaces;

import hse.kpo.domains.Customer;
import java.util.List;

/**
 * Interface for customers provider.
 */
public interface InterfaceCustomerProvider {
    // метод возвращает коллекцию только для чтения
    List<Customer> getCustomers();
}
