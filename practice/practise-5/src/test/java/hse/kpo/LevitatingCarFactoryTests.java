package hse.kpo;

import hse.kpo.domains.Car;
import hse.kpo.domains.LevitatingEngine;
import hse.kpo.factories.LevitatingCarFactory;
import hse.kpo.params.EmptyEngineParams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LevitatingCarFactoryTests {

    @Test
    @DisplayName("Тест создания левитирующий машины")
    void levitatingCreateTest() {

        var levitatingCarFactory = new LevitatingCarFactory();
        var fabricLevitatingCar = levitatingCarFactory.createCar(EmptyEngineParams.DEFAULT, 1);

        var realLevitatingCar = new Car(1, new LevitatingEngine());

        Assertions.assertEquals(fabricLevitatingCar, realLevitatingCar);

    }

}
