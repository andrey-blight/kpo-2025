package hse.kpo.interfaces;

import hse.kpo.domains.Catamaran;
import hse.kpo.domains.Customer;

/**
 * Interface for customers provider.
 */
public interface InterfaceCatamaranProvider {

    Catamaran takeCatamaran(Customer customer); // Метод возвращает optional на Car, что означает, что метод может ничего не вернуть
}
