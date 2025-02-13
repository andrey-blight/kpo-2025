package org.example.fabrics;

import org.example.domains.Mammal;
import org.example.domains.Monkey;
import org.example.domains.Rabbit;
import org.example.interfaces.InterfaceAnimalFabric;
import org.example.params.MammalParams;

public class MammalFabric implements InterfaceAnimalFabric<MammalParams> {

    public Mammal createAnimal(MammalParams params) {
        return switch (params.type().toLowerCase()) {
            case "rabbit" -> new Rabbit(params.kindness(), params.foodConsumption(), params.name());
            case "monkey" -> new Monkey(params.kindness(), params.foodConsumption(), params.name());
            default -> throw new IllegalArgumentException("Неизвестное животное: " + params.type());
        };
    }
}
