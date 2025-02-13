package org.example;

import org.example.domains.Rabbit;
import org.example.fabrics.MammalFabric;
import org.example.fabrics.PredatorFabric;
import org.example.params.MammalParams;
import org.example.params.PredatorParams;
import org.example.services.AnimalStorage;
import org.example.services.Clinic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AnimalStorageTests {
    @Autowired
    private Clinic clinic;

    @Autowired
    private AnimalStorage storage;

    @Test
    @DirtiesContext
    @DisplayName("Тест создания кролика")
    public void testCreateRabbit() {

        storage.addAnimal(new MammalFabric(),
                new MammalParams("rabbit", "Nik", 10, 12),
                clinic);

        var example = new Rabbit(12, 10, "Nik");

        assertEquals(example.toString(), storage.getAnimals().getFirst().toString());
    }

    @Test
    @DirtiesContext
    @DisplayName("Тест получения кол-ва корма для всех животных")
    public void testGetFeedCount() {

        storage.addAnimal(new MammalFabric(),
                new MammalParams("rabbit", "Nik", 10, 12),
                clinic);

        storage.addAnimal(new MammalFabric(),
                new MammalParams("monkey", "Mik", 10, 5),
                clinic);

        storage.addAnimal(new PredatorFabric(),
                new PredatorParams("tiger", "Max", 12),
                clinic);

        assertEquals(32, storage.getTotalFeed());

    }

    @Test
    @DirtiesContext
    @DisplayName("Тест получения отчета")
    public void testGetInfo() {

        storage.addAnimal(new MammalFabric(),
                new MammalParams("rabbit", "Nik", 10, 12),
                clinic);

        storage.addAnimal(new MammalFabric(),
                new MammalParams("monkey", "Mik", 10, 5),
                clinic);

        storage.addAnimal(new PredatorFabric(),
                new PredatorParams("tiger", "Max", 10),
                clinic);

        storage.print_info();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            storage.print_info();
            assertEquals("""
                    Total animals: 3
                    
                    All animals:
                    food Consumption: 10
                    name: Rabbit Nik
                    kindness: 12
                    type: Rabbit
                    
                    food Consumption: 10
                    name: Monkey Mik
                    kindness: 5
                    type: Monkey
                    
                    food Consumption: 10
                    name: Tiger Max
                    type: Tiger
                    
                    
                    Animals for contact zoo
                    food Consumption: 10
                    name: Rabbit Nik
                    kindness: 12
                    type: Rabbit
                    
                    """, outContent.toString());
        } finally {
            System.setOut(originalOut);
        }
    }

}

