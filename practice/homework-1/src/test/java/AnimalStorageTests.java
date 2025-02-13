import org.example.domains.Clinic;
import org.example.domains.Rabbit;
import org.example.fabrics.MammalFabric;
import org.example.fabrics.PredatorFabric;
import org.example.params.MammalParams;
import org.example.params.PredatorParams;
import org.example.services.AnimalStorage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AnimalStorageTests {

    @Test
    @DisplayName("Тест создания кролика")
    public void testCreateRabbit() {
        var storage = new AnimalStorage();
        var clinic = new Clinic();

        storage.addAnimal(new MammalFabric(),
                new MammalParams("rabbit", "Nik", 10, 12),
                clinic);

        var example = new Rabbit(12, 10, "Nik");

        assertEquals(example.toString(), storage.getAnimals().getFirst().toString());
    }

    @Test
    @DisplayName("Создаем животное, которое не пройдет проверку в клинике")
    public void testCreateBadAnimal() {
        var storage = new AnimalStorage();
        var clinic = new Clinic();

        storage.addAnimal(new MammalFabric(),
                new MammalParams("rabbit", "Nik", 200, 12),
                clinic);

        assertTrue(storage.getAnimals().isEmpty());
    }

    @Test
    @DisplayName("Тест получения кол-ва корма для всех животных")
    public void testGetFeedCount() {
        var storage = new AnimalStorage();
        var clinic = new Clinic();

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
    @DisplayName("Тест получения отчета")
    public void testGetInfo() {
        var storage = new AnimalStorage();
        var clinic = new Clinic();

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
            assertEquals("Total animals: 3\n" +
                    "\n" +
                    "All animals:\n" +
                    "food Consumption: 10\n" +
                    "name: Rabbit Nik\n" +
                    "kindness: 12\n" +
                    "type: Rabbit\n" +
                    "\n" +
                    "food Consumption: 10\n" +
                    "name: Monkey Mik\n" +
                    "kindness: 5\n" +
                    "type: Monkey\n" +
                    "\n" +
                    "food Consumption: 10\n" +
                    "name: Tiger Max\n" +
                    "type: Tiger\n" +
                    "\n" +
                    "\n" +
                    "Animals for contact zoo\n" +
                    "food Consumption: 10\n" +
                    "name: Rabbit Nik\n" +
                    "kindness: 12\n" +
                    "type: Rabbit\n" +
                    "\n", outContent.toString());
        } finally {
            System.setOut(originalOut);
        }
    }

}

