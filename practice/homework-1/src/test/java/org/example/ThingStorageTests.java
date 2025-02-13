package org.example;

import org.example.domains.Computer;
import org.example.domains.Table;
import org.example.services.ThingStorage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


@SpringBootTest
public class ThingStorageTests {
    @Autowired
    private ThingStorage storage;

    @Test
    @DirtiesContext
    @DisplayName("Тест создания компьютера")
    public void testCreateComputer() {
        storage.addThing("computer");

        var example = new Computer(0);

        assertEquals(storage.getThings().getFirst().toString(), example.toString());
    }

    @Test
    @DirtiesContext
    @DisplayName("Тест создания стола")
    public void testCreateTable() {
        storage.addThing("table");

        var example = new Table(0);

        assertEquals(storage.getThings().getFirst().toString(), example.toString());
    }

    @Test
    @DirtiesContext
    @DisplayName("Тест получения отчета")
    public void testGetInfo() {
        storage.addThing("table");
        storage.addThing("table");
        storage.addThing("computer");

        storage.print_info();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            storage.print_info();
            assertEquals("""
                    Total things: 3
                    
                    All things:
                    number: 0
                    type: Table
                    
                    number: 1
                    type: Table
                    
                    number: 2
                    type: Computer
                    
                    """, outContent.toString());
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    @DirtiesContext
    @DisplayName("Тест создания неизвестного предмета")
    public void testCreateUnknown() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> storage.addThing("chair"));

        assertEquals("Неизвестная вещь: chair", exception.getMessage());
    }
}
