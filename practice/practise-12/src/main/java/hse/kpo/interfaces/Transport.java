package hse.kpo.interfaces;

import hse.kpo.domains.customers.Customer;

public interface Transport {
    boolean isCompatible(Customer customer);

    int getVin();

    String getEngineType();

    String getTransportType();
}
