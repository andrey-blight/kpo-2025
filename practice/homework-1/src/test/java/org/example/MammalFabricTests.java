package org.example;

import org.example.domains.Monkey;
import org.example.domains.Rabbit;
import org.example.fabrics.MammalFabric;
import org.example.params.MammalParams;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class MammalFabricTests {
    @Autowired
    private MammalFabric fabric;

    @Test
    @DirtiesContext
    @DisplayName("Тест создания кролика")
    public void testCreateRabbit() {
        Rabbit example = new Rabbit(10, 12, "Nik");
        Rabbit rabbit = (Rabbit) fabric.createAnimal(new MammalParams("rabbit", "Nik", 12, 10));

        assertEquals(rabbit.toString(), example.toString());
    }

    @Test
    @DirtiesContext
    @DisplayName("Тест создания обезьяны")
    public void testCreateMonkey() {
        var example = new Monkey(10, 12, "Nik");
        Monkey monkey = (Monkey) fabric.createAnimal(new MammalParams("monkey", "Nik", 12, 10));

        assertEquals(monkey.toString(), example.toString());
    }

    @Test
    @DirtiesContext
    @DisplayName("Тест создания неизвестного животного")
    public void testCreateUnknown() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                fabric.createAnimal(new MammalParams("dragon", "Draco", 10, 5)));

        assertEquals("Неизвестное животное: dragon", exception.getMessage());
    }
}
