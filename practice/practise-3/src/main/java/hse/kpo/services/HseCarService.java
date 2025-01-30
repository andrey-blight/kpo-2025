package hse.kpo.services;

import hse.kpo.interfaces.InterfaceCarProvider;
import hse.kpo.interfaces.InterfaceCustomerProvider;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Seller class.
 */
@Component
@RequiredArgsConstructor
public class HseCarService {

    private final InterfaceCarProvider carProvider;

    private final InterfaceCustomerProvider customerProvider;

    /**
     * Sell car for all customers.
     */
    public void sellCars() {
        // получаем список покупателей
        var customers = customerProvider.getCustomers();
        // пробегаемся по полученному списку
        customers.stream().filter(customer -> Objects.isNull(customer.getCar()))
                .forEach(customer -> {
                    var car = carProvider.takeCar(customer);
                    if (Objects.nonNull(car)) {
                        customer.setCar(car);
                    }
                });
    }
}