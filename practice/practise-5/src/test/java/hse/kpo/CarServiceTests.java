package hse.kpo;

import hse.kpo.domains.Car;
import hse.kpo.domains.Customer;
import hse.kpo.domains.HandEngine;
import hse.kpo.factories.HandCarFactory;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.services.CarService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class CarServiceTests {

    @Test
    @DisplayName("Тест добавления машины в сервис машин")
    void addCarTest() {

        var carService = new CarService();
        var handCarFactory = new HandCarFactory();

        carService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);

        var realCar = new Car(1, new HandEngine());
        List<Car> cars = new ArrayList<>();
        cars.add(realCar);

        Assertions.assertEquals(cars, carService.getCars());
    }

    @Test
    @DisplayName("Тест продажи машины клиенту")
    void sellCarTest() {

        var carService = new CarService();
        var handCarFactory = new HandCarFactory();

        carService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);

        var customer = new Customer("Ivan1", 6, 6, 100);

        var customerCar = carService.takeCar(customer);
        var realCar = new Car(1, new HandEngine());

        Assertions.assertEquals(customerCar, realCar);
    }

    @Test
    @DisplayName("Тест продажи машины клиента, для которого нет подходящих машин")
    void cantSellCarTest() {

        var carService = new CarService();
        var handCarFactory = new HandCarFactory();

        carService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);

        var customer = new Customer("Ivan1", 1, 1, 100);

        var customerCar = carService.takeCar(customer);

        Assertions.assertNull(customerCar);
    }

}
