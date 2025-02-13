package org.example;

import org.example.domains.Tiger;
import org.example.domains.Wolf;
import org.example.fabrics.PredatorFabric;
import org.example.params.PredatorParams;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PredatorFabricTests {
    @Autowired
    private PredatorFabric fabric;

    @Test
    @DirtiesContext
    @DisplayName("Тест создания тигра")
    public void testCreateTiger() {
        var example = new Tiger(12, "Nik");
        Tiger tiger = (Tiger) fabric.createAnimal(new PredatorParams("tiger", "Nik", 12));

        assertEquals(tiger.toString(), example.toString());
    }

    @Test
    @DirtiesContext
    @DisplayName("Тест создания волка")
    public void testCreateWolf() {
        var example = new Wolf(10, "Nik");
        Wolf wolf = (Wolf) fabric.createAnimal(new PredatorParams("wolf", "Nik", 10));

        assertEquals(wolf.toString(), example.toString());
    }

    @Test
    @DirtiesContext
    @DisplayName("Тест создания неизвестного животного")
    public void testCreateUnknown() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> fabric.createAnimal(new PredatorParams("dragon", "Draco", 10)));

        assertEquals("Неизвестное животное: dragon", exception.getMessage());
    }
}
