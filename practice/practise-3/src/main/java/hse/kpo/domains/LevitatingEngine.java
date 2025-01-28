package hse.kpo.domains;

import hse.kpo.interfaces.IEngine;
import lombok.ToString;

@ToString
public class LevitatingEngine implements IEngine {
    @Override
    public boolean isCompatible(Customer customer) {
        return customer.getIq() > 300;
    }
}
