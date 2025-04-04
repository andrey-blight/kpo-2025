package hse.kpo.interfaces;


import hse.kpo.domains.Customer;

/**
 * Interface for engine.
 */
public interface InterfaceEngine {

    /**
     * Метод для проверки совместимости двигателя с покупателем.
     *
     * @param customer - покупатель, с которым мы сравниваем двигатель
     * @return true, если двигатель подходит покупателю
     */
    boolean isCompatible(Customer customer);
}
