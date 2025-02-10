package hse.kpo.domains;

import hse.kpo.interfaces.InterfaceEngine;
import lombok.Getter;
import lombok.ToString;

/**
 * Car class with engine.
 */
@ToString
public class Car {

    private final InterfaceEngine engine;

    @Getter
    private final int vin;

    public Car(int vin, InterfaceEngine engine) {
        this.vin = vin;
        this.engine = engine;
    }

    public boolean isCompatible(Customer customer) {
        return this.engine.isCompatible(customer); // внутри метода просто вызываем соответствующий метод двигателя
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // Сравнение по ссылке
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false; // Проверка типа
        }

        var car = (Car) obj;
        return vin == car.vin && engine.getClass() == car.engine.getClass(); // Сравнение полей
    }
}
