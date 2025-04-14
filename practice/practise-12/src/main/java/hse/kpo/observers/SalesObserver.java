package hse.kpo.observers;

import hse.kpo.domains.Report;
import hse.kpo.domains.customers.Customer;
import hse.kpo.enums.ProductionTypes;

public interface SalesObserver {
    void onSale(Customer customer, ProductionTypes productType, int vin);

    void checkCustomers();

    Report buildReport();
}
