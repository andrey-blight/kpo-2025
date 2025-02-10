package hse.kpo.factories;

import hse.kpo.domains.Car;
import hse.kpo.domains.LevitatingEngine;
import hse.kpo.interfaces.InterfaceCarFactory;
import hse.kpo.params.EmptyEngineParams;

/**
 * Factory for levitating cars.
 */
public class LevitatingCarFactory implements InterfaceCarFactory<EmptyEngineParams> {
    @Override
    public Car createCar(EmptyEngineParams carParams, int carNumber) {
        var engine = new LevitatingEngine(); // Создаем двигатель без каких-либо параметров

        return new Car(carNumber, engine); // создаем автомобиль с ручным приводом
    }
}
