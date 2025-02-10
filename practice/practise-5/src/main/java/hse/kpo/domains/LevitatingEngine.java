package hse.kpo.domains;

import hse.kpo.interfaces.InterfaceEngine;
import lombok.ToString;

/**
 * Levitating engine (can own only with iq > 300).
 */
@ToString
public class LevitatingEngine implements InterfaceEngine {
    @Override
    public boolean isCompatible(Customer customer) {
        return customer.getIq() > 300;
    }
}
