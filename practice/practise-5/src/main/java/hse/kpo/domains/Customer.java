package hse.kpo.domains;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


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

    @Setter
    private Catamaran catamaran;

    /**
     * Create customer with param.
     */
    public Customer(String name, int legPower, int handPower, int iq) {
        this.name = name;
        this.legPower = legPower;
        this.handPower = handPower;
        this.iq = iq;
    }
}
