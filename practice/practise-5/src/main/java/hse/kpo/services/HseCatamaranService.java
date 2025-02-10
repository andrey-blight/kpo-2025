package hse.kpo.services;

import hse.kpo.interfaces.InterfaceCatamaranProvider;
import hse.kpo.interfaces.InterfaceCustomerProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Seller class.
 */
@Component
@RequiredArgsConstructor
public class HseCatamaranService {

    private final InterfaceCatamaranProvider catamaranProvider;

    private final InterfaceCustomerProvider customerProvider;

    /**
     * Sell catamarans for all customers.
     */
    public void sellCatamarans() {
        // получаем список покупателей
        var customers = customerProvider.getCustomers();
        // пробегаемся по полученному списку
        customers.stream().filter(customer -> Objects.isNull(customer.getCatamaran()))
                .forEach(customer -> {
                    var catamaran = catamaranProvider.takeCatamaran(customer);
                    if (Objects.nonNull(catamaran)) {
                        customer.setCatamaran(catamaran);
                    }
                });
    }
}