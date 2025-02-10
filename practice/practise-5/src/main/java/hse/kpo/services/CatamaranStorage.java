package hse.kpo.services;

import hse.kpo.domains.Catamaran;
import hse.kpo.domains.Customer;
import hse.kpo.interfaces.InterfaceCatamaranFactory;
import hse.kpo.interfaces.InterfaceCatamaranProvider;
import lombok.Getter;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * Sell car class.
 */
@Component
public class CatamaranStorage implements InterfaceCatamaranProvider {

    @Getter
    private final List<Catamaran> catamarans = new ArrayList<>();

    private int catamaranNumberCounter = 0;

    @Override
    public Catamaran takeCatamaran(Customer customer) {

        var filteredCatamarans = catamarans.stream().filter(catamaran -> catamaran.isCompatible(customer)).toList();

        var firstCatamarans = filteredCatamarans.stream().findFirst();

        firstCatamarans.ifPresent(catamarans::remove);

        return firstCatamarans.orElse(null);
    }

    /**
     * add car to list of cars.
     */
    public <T> void addCatamaran(InterfaceCatamaranFactory<T> catamaranFactory, T catamaranParams) {
        // создаем автомобиль из переданной фабрики
        var catamaran = catamaranFactory.createCatamaran(
                catamaranParams, // передаем параметры
                ++catamaranNumberCounter // передаем номер - номер будет начинаться с 1
        );

        catamarans.add(catamaran); // добавляем автомобиль
    }
}
