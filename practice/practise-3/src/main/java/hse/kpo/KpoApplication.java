package hse.kpo;

import hse.kpo.domains.Customer;
import hse.kpo.factories.HandCarFactory;
import hse.kpo.factories.LevitatingCarFactory;
import hse.kpo.factories.PedalCarFactory;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.services.CarService;
import hse.kpo.services.CustomerStorage;
import hse.kpo.services.HseCarService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class.
 */
@SpringBootApplication
public class KpoApplication {

    /**
     * Main function.
     */
    public static void main(String[] args) {
        SpringApplication.run(KpoApplication.class, args);

        var customerStorage = new CustomerStorage();

        customerStorage.addCustomer(new Customer("Ivan1", 6, 4, 100));
        customerStorage.addCustomer(new Customer("Maksim", 4, 6, 200));
        customerStorage.addCustomer(new Customer("Petya", 6, 6, 301));
        customerStorage.addCustomer(new Customer("Nikita", 4, 4, 400));

        var carService = new CarService();

        var levitatingCarFactory = new LevitatingCarFactory();
        carService.addCar(levitatingCarFactory, EmptyEngineParams.DEFAULT);

        var pedalCarFactory = new PedalCarFactory();
        carService.addCar(pedalCarFactory, new PedalEngineParams(6));
        carService.addCar(pedalCarFactory, new PedalEngineParams(6));

        var handCarFactory = new HandCarFactory();
        carService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);

        customerStorage.getCustomers().stream().map(Customer::toString).forEach(System.out::println);

        var hseCarService = new HseCarService(carService, customerStorage);
        hseCarService.sellCars();

        customerStorage.getCustomers().stream().map(Customer::toString).forEach(System.out::println);
    }
}
