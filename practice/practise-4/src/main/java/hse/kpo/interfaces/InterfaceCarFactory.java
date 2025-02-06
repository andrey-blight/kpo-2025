package hse.kpo.interfaces;

import hse.kpo.domains.Car;

/**
 * Interface for car.
 */
public interface InterfaceCarFactory<T> {
    Car createCar(T carParams, int carNumber);
}
