package hse.kpo;

import hse.kpo.domains.Car;
import hse.kpo.domains.PedalEngine;
import hse.kpo.factories.PedalCarFactory;
import hse.kpo.params.PedalEngineParams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class PedalCarFactoryTests {

    @Test
    @DisplayName("Тест создания педальной машины")
    void pedalCreateTest() {

        var pedalCarFactory = new PedalCarFactory();
        var fabricPedalCar = pedalCarFactory.createCar(new PedalEngineParams(6), 2);

        var realPedalCar = new Car(2, new PedalEngine(6));

        Assertions.assertEquals(fabricPedalCar, realPedalCar);

    }

}
