package hse.kpo;

import hse.kpo.domains.Car;
import hse.kpo.domains.HandEngine;
import hse.kpo.factories.HandCarFactory;
import hse.kpo.params.EmptyEngineParams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class HandCarFactoryTests {

    @Test
    @DisplayName("Тест создания ручной машины")
    void handCreateTest() {

        var handCarFactory = new HandCarFactory();
        var fabricHandCar = handCarFactory.createCar(EmptyEngineParams.DEFAULT, 3);

        var realHandHar = new Car(3, new HandEngine());

        Assertions.assertEquals(fabricHandCar, realHandHar);

    }

}

