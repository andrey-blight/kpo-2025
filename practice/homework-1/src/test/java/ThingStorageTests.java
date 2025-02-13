import org.example.domains.Computer;
import org.example.domains.Table;
import org.example.services.ThingStorage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ThingStorageTests {
    @Test
    @DisplayName("Тест создания компьютера")
    public void testCreateComputer() {
        var storage = new ThingStorage();
        storage.addThing("computer");

        var example = new Computer(0);

        assertEquals(storage.getThings().getFirst().toString(), example.toString());
    }

    @Test
    @DisplayName("Тест создания стола")
    public void testCreateTable() {
        var storage = new ThingStorage();
        storage.addThing("table");

        var example = new Table(0);

        assertEquals(storage.getThings().getFirst().toString(), example.toString());
    }

    @Test
    @DisplayName("Тест получения отчета")
    public void testGetInfo() {
        var storage = new ThingStorage();
        storage.addThing("table");
        storage.addThing("table");
        storage.addThing("computer");

        storage.print_info();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            storage.print_info();
            assertEquals("Total things: 3\n" +
                    "\n" +
                    "All things:\n" +
                    "number: 0\n" +
                    "type: Table\n" +
                    "\n" +
                    "number: 1\n" +
                    "type: Table\n" +
                    "\n" +
                    "number: 2\n" +
                    "type: Computer\n\n", outContent.toString());
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    @DisplayName("Тест создания неизвестного предмета")
    public void testCreateUnknown() {
        var storage = new ThingStorage();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            storage.addThing("chair");
        });

        assertEquals("Неизвестная вещь: chair", exception.getMessage());
    }
}
