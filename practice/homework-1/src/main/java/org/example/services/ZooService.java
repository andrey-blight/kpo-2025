package org.example.services;

import org.example.domains.Clinic;
import org.example.interfaces.InterfaceAnimalFabric;
import org.springframework.stereotype.Component;

@Component
public class ZooService {

    private final AnimalStorage animalStorage;
    private final Clinic clinic;
    private final ThingStorage thingStorage;

    public ZooService() {
        this.animalStorage = new AnimalStorage();
        this.clinic = new Clinic();
        this.thingStorage = new ThingStorage();
    }

    public <T> void addAnimal(InterfaceAnimalFabric<T> animalFabric, T params) {
        animalStorage.addAnimal(animalFabric, params, clinic);
    }

    public int getTotalFeed() {
        return animalStorage.getTotalFeed();
    }

    public void print_info() {
        thingStorage.print_info();
        System.out.println();
        animalStorage.print_info();
    }

}
