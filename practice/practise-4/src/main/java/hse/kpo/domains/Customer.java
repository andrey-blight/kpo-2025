package hse.kpo.domains;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

/**
 * Customer class.
 */
@Getter
@ToString
public class Customer {
    private final String name;

    private final int legPower;

    private final int handPower;

    private final int iq;

    @Setter
    private Car car;

    /**
     * Create customer with param.
     */
    public Customer(String name, int legPower, int handPower, int iq) {
        this.name = name;
        this.legPower = legPower;
        this.handPower = handPower;
        this.iq = iq;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Сравнение по ссылке

        if (obj == null || getClass() != obj.getClass()) return false; // Проверка типа

        var customer = (Customer) obj;
        return Objects.equals(name, customer.getName()) && legPower == customer.getLegPower() &&
                handPower == customer.getHandPower() && iq == customer.getIq(); // Сравнение полей
    }
}
