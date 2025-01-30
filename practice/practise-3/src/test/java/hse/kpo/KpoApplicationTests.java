package hse.kpo;

import hse.kpo.domains.*;
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

import static org.mockito.Mockito.*;

@SpringBootTest
class KpoApplicationTests {

    @Autowired
    private CarService carService;

    @Autowired
    private CustomerStorage customerStorage;

    @Autowired
    private HseCarService hseCarService;


    @Test
    @DisplayName("Тест загрузки контекста")
    void contextLoads() {

        customerStorage.addCustomer(new Customer("Ivan1", 6, 4, 100));
        customerStorage.addCustomer(new Customer("Maksim", 4, 6, 200));
        customerStorage.addCustomer(new Customer("Petya", 6, 6, 301));
        customerStorage.addCustomer(new Customer("Nikita", 4, 4, 400));

        var levitatingCarFactory = new LevitatingCarFactory();
        carService.addCar(levitatingCarFactory, EmptyEngineParams.DEFAULT);

        var pedalCarFactory = new PedalCarFactory();
        carService.addCar(pedalCarFactory, new PedalEngineParams(6));
        carService.addCar(pedalCarFactory, new PedalEngineParams(6));

        var handCarFactory = new HandCarFactory();
        carService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);

        customerStorage.getCustomers().stream().map(Customer::toString).forEach(System.out::println);

        hseCarService.sellCars();

        customerStorage.getCustomers().stream().map(Customer::toString).forEach(System.out::println);
    }

    @Test
    @DisplayName("Тест создания левитирующий машины")
    void levitatingFabricTest() {

        var levitatingCarFactory = new LevitatingCarFactory();
        var fabric_levitating_car = levitatingCarFactory.createCar(EmptyEngineParams.DEFAULT, 1);

        var real_levitating_car = new Car(1, new LevitatingEngine());

        Assertions.assertEquals(fabric_levitating_car, real_levitating_car);

    }

    @Test
    @DisplayName("Тест создания педальной машины")
    void pedalFabricTest() {

        var pedalCarFactory = new PedalCarFactory();
        var fabric_pedal_car = pedalCarFactory.createCar(new PedalEngineParams(6), 2);

        var real_pedal_car = new Car(2, new PedalEngine(6));

        Assertions.assertEquals(fabric_pedal_car, real_pedal_car);

    }

    @Test
    @DisplayName("Тест создания ручной машины")
    void handFabricTest() {

        var handCarFactory = new HandCarFactory();
        var fabric_hand_car = handCarFactory.createCar(EmptyEngineParams.DEFAULT, 3);

        var real_hand_car = new Car(3, new HandEngine());

        Assertions.assertEquals(fabric_hand_car, real_hand_car);

    }

    @Test
    @DisplayName("Тест добавления машины в сервис машин")
    void addCarToServiceTest() {

        var spyCarService = spy(carService);
        var handCarFactory = new HandCarFactory();

        doNothing().when(spyCarService).addCar(handCarFactory, EmptyEngineParams.DEFAULT);
        spyCarService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);

        verify(spyCarService, times(1)).addCar(handCarFactory, EmptyEngineParams.DEFAULT);

    }

    @Test
    @DisplayName("Тест получения машины из сервиса машин")
    void getFromCarServiceTest() {

        var mockCarService = mock(CarService.class);

        var customer = new Customer("Ivan1", 6, 4, 100);

        when(mockCarService.takeCar(customer)).thenReturn(null);

        mockCarService.takeCar(customer);

        verify(mockCarService, times(1)).takeCar(customer);

    }

    @Test
    @DisplayName("Тест добавления покупателя в список покупателей")
    void addToCustomersTest() {

        var mockCustomerStorage = mock(CustomerStorage.class);

        var customer = new Customer("Ivan1", 6, 4, 100);

        doNothing().when(mockCustomerStorage).addCustomer(customer);

        mockCustomerStorage.addCustomer(customer);

        verify(mockCustomerStorage, times(1)).addCustomer(customer);

    }

    @Test
    @DisplayName("Тест получения всех покупателей")
    void getAllCustomersTest() {

        var mockCustomerStorage = mock(CustomerStorage.class);

        when(mockCustomerStorage.getCustomers()).thenReturn(null);

        mockCustomerStorage.getCustomers();

        verify(mockCustomerStorage, times(1)).getCustomers();

    }

    @Test
    @DisplayName("Тест продажи машин клиентам")
    void sellAllCarsTest() {

        var mockCustomerStorage = mock(HseCarService.class);

        doNothing().when(mockCustomerStorage).sellCars();

        mockCustomerStorage.sellCars();

        verify(mockCustomerStorage, times(1)).sellCars();

    }

}
