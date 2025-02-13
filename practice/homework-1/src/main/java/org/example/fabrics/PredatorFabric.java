package org.example.fabrics;

import org.example.domains.Animal;
import org.example.domains.Wolf;
import org.example.domains.Tiger;
import org.example.interfaces.InterfaceAnimalFabric;
import org.example.params.PredatorParams;

public class PredatorFabric implements InterfaceAnimalFabric<PredatorParams> {

    public Animal createAnimal(PredatorParams params) {
        return switch (params.type().toLowerCase()) {
            case "wolf" -> new Wolf(params.foodConsumption(), params.name());
            case "tiger" -> new Tiger(params.foodConsumption(), params.name());
            default -> throw new IllegalArgumentException("Неизвестное животное: " + params.type());
        };
    }
}
