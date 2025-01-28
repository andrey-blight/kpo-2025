package hse.kpo.domains;

import hse.kpo.interfaces.InterfaceEngine;
import lombok.Getter;
import lombok.ToString;

/**
 * Car class with engine.
 */
@ToString
public class Car {

    private InterfaceEngine engine;

    @Getter
    private int vin;

    public Car(int vin, InterfaceEngine engine) {
        this.vin = vin;
        this.engine = engine;
    }

    public boolean isCompatible(Customer customer) {
        return this.engine.isCompatible(customer); // внутри метода просто вызываем соответствующий метод двигателя
    }
}
