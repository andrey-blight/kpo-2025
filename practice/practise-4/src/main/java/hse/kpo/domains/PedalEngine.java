package hse.kpo.domains;

import hse.kpo.interfaces.InterfaceEngine;
import lombok.Getter;
import lombok.ToString;

/**
 * Pedal engine class (can own only with leg power > 5).
 */
@ToString
@Getter

public class PedalEngine implements InterfaceEngine {
    private final int size;

    @Override
    public boolean isCompatible(Customer customer) {
        return customer.getLegPower() > 5;
    }

    public PedalEngine(int size) {
        this.size = size;
    }
}
