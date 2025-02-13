package org.example.services;

import lombok.Getter;
import org.example.domains.Animal;
import org.example.domains.Clinic;
import org.example.domains.Mammal;
import org.example.interfaces.InterfaceAnimalFabric;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Getter
@Component
public class AnimalStorage {
    private final List<Animal> animals = new ArrayList<>();

    public <T> void addAnimal(InterfaceAnimalFabric<T> animalFabric, T params, Clinic clinic) {
        var animal = animalFabric.createAnimal(params);

        if (!clinic.check_health(animal)) {
            return;
        }

        animals.add(animal);
    }

    public int getTotalFeed() {
        return animals.stream().mapToInt(Animal::getFoodConsumption).sum();
    }

    public void print_info() {
        System.out.println("Total animals: " + animals.size() + "\n");
        System.out.println("All animals:");
        animals.stream().map(Animal::toString).forEach(System.out::println);
        System.out.println("\nAnimals for contact zoo");
        animals.stream().filter(animal -> animal instanceof Mammal)
                .map(mammal -> (Mammal) mammal)
                .filter(mammal -> mammal.getKindness() >= 10)
                .map(Mammal::toString).forEach(System.out::println);
    }
}
