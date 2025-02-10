package hse.kpo;

import hse.kpo.domains.Customer;
import hse.kpo.services.CustomerStorage;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class CustomerStorageTests {

    @Test
    @DisplayName("Тест добавления клиента в список клиентов")
    void addCustomerTest() {
        var customerStorage = new CustomerStorage();
        var customer = new Customer("Ivan1", 6, 6, 100);

        customerStorage.addCustomer(customer);

        List<Customer> realCustomers = new ArrayList<>();
        realCustomers.add(customer);

        Assertions.assertEquals(realCustomers, customerStorage.getCustomers());
    }

}