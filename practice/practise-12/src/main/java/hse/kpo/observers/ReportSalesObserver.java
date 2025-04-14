package hse.kpo.observers;

import hse.kpo.builders.ReportBuilder;
import hse.kpo.domains.Report;
import hse.kpo.domains.customers.Customer;
import hse.kpo.enums.ProductionTypes;
import hse.kpo.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportSalesObserver implements SalesObserver {
    private final CustomerRepository customerRepository;

    private final ReportBuilder reportBuilder = new ReportBuilder();

    public Report buildReport() {
        return reportBuilder.build();
    }

    public void checkCustomers() {
        reportBuilder.addCustomers(customerRepository.getCustomers());
    }

    @Override
    public void onSale(Customer customer, ProductionTypes productType, int vin) {
        String message = String.format(
                "Продажа: %s VIN-%d клиенту %s (Сила рук: %d, Сила ног: %d, IQ: %d)",
                productType, vin, customer.getName(),
                customer.getHandPower(), customer.getLegPower(), customer.getIq()
        );
        reportBuilder.addOperation(message);
    }
}
