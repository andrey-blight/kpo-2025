package hse.kpo.factories;


import hse.kpo.domains.Car;
import hse.kpo.domains.PedalEngine;
import hse.kpo.interfaces.InterfaceCarFactory;
import hse.kpo.params.PedalEngineParams;

/**
 * Factory for pedal cars.
 */
public class PedalCarFactory implements InterfaceCarFactory<PedalEngineParams> {
    @Override
    public Car createCar(PedalEngineParams carParams, int carNumber) {
        var engine = new PedalEngine(carParams.pedalSize()); // создаем двигатель на основе переданных параметров

        // возвращаем собранный автомобиль с установленным двигателем и определенным номером
        return new Car(carNumber, engine);
    }
}
