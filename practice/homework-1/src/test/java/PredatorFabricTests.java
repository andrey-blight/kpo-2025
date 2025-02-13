import org.example.domains.Tiger;
import org.example.domains.Wolf;
import org.example.fabrics.PredatorFabric;
import org.example.params.PredatorParams;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class PredatorFabricTests {

    @Test
    @DisplayName("Тест создания тигра")
    public void testCreateTiger() {
        var fabric = new PredatorFabric();
        var example = new Tiger(12, "Nik");
        Tiger tiger = (Tiger) fabric.createAnimal(new PredatorParams("tiger", "Nik", 12));

        assertEquals(tiger.toString(), example.toString());
    }

    @Test
    @DisplayName("Тест создания волка")
    public void testCreateWolf() {
        var fabric = new PredatorFabric();
        var example = new Wolf(10, "Nik");
        Wolf wolf = (Wolf) fabric.createAnimal(new PredatorParams("wolf", "Nik", 10));

        assertEquals(wolf.toString(), example.toString());
    }

    @Test
    @DisplayName("Тест создания неизвестного животного")
    public void testCreateUnknown() {
        var fabric = new PredatorFabric();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            fabric.createAnimal(new PredatorParams("dragon", "Draco", 10));
        });

        assertEquals("Неизвестное животное: dragon", exception.getMessage());
    }
}
