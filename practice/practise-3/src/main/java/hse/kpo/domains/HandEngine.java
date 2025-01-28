package hse.kpo.domains;

import hse.kpo.interfaces.InterfaceEngine;
import lombok.ToString;

/**
 * Hand engine class (can own only with hand power > 5).
 */
@ToString

public class HandEngine implements InterfaceEngine {
    /**
     * Check can customer own this engine.
     */
    @Override
    public boolean isCompatible(Customer customer) {
        return customer.getHandPower() > 5;
    }
}
