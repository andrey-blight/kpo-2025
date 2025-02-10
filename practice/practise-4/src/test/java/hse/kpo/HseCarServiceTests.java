package hse.kpo;

import hse.kpo.domains.Car;
import hse.kpo.domains.Customer;
import hse.kpo.domains.HandEngine;
import hse.kpo.domains.LevitatingEngine;
import hse.kpo.domains.PedalEngine;
import hse.kpo.factories.HandCarFactory;
import hse.kpo.factories.LevitatingCarFactory;
import hse.kpo.factories.PedalCarFactory;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.services.CarService;
import hse.kpo.services.CustomerStorage;
import hse.kpo.services.HseCarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class HseCarServiceTests {

    @Autowired
    private CarService carService;

    @Autowired
    private CustomerStorage customerStorage;

    @Autowired
    private HseCarService hseCarService;


    @Test
    @DisplayName("Тест продажи всех автомобилей клиентам")
    void contextLoads() {
        var ivan = new Customer("Ivan", 6, 4, 100);
        var maxim = new Customer("Maxim", 4, 6, 200);
        var petya = new Customer("Petya", 6, 6, 301);
        var nikita = new Customer("Nikita", 4, 4, 400);

        customerStorage.addCustomer(ivan);
        customerStorage.addCustomer(maxim);
        customerStorage.addCustomer(petya);
        customerStorage.addCustomer(nikita);

        var levitatingCarFactory = new LevitatingCarFactory();
        carService.addCar(levitatingCarFactory, EmptyEngineParams.DEFAULT);

        var pedalCarFactory = new PedalCarFactory();
        carService.addCar(pedalCarFactory, new PedalEngineParams(6));
        carService.addCar(pedalCarFactory, new PedalEngineParams(6));

        var handCarFactory = new HandCarFactory();
        carService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);

        hseCarService.sellCars();

        var ivanCar = new Car(2, new PedalEngine(6));
        var maximCar = new Car(4, new HandEngine());
        var petyaCar = new Car(1, new LevitatingEngine());

        Assertions.assertEquals(ivan.getCar(), ivanCar);
        Assertions.assertEquals(maxim.getCar(), maximCar);
        Assertions.assertEquals(petya.getCar(), petyaCar);
        Assertions.assertNull(nikita.getCar());
    }
}