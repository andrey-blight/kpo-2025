import org.example.domains.Monkey;
import org.example.domains.Rabbit;
import org.example.fabrics.MammalFabric;
import org.example.params.MammalParams;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MammalFabricTests {

    @Test
    @DisplayName("Тест создания кролика")
    public void testCreateRabbit() {
        var fabric = new MammalFabric();
        Rabbit example = new Rabbit(10, 12, "Nik");
        Rabbit rabbit = (Rabbit) fabric.createAnimal(new MammalParams("rabbit", "Nik", 12, 10));

        assertEquals(rabbit.toString(), example.toString());
    }

    @Test
    @DisplayName("Тест создания обезьяны")
    public void testCreateMonkey() {
        var fabric = new MammalFabric();
        var example = new Monkey(10, 12, "Nik");
        Monkey monkey = (Monkey) fabric.createAnimal(new MammalParams("monkey", "Nik", 12, 10));

        assertEquals(monkey.toString(), example.toString());
    }

    @Test
    @DisplayName("Тест создания неизвестного животного")
    public void testCreateUnknown() {
        var fabric = new MammalFabric();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            fabric.createAnimal(new MammalParams("dragon", "Draco", 10, 5));
        });

        assertEquals("Неизвестное животное: dragon", exception.getMessage());
    }
}
