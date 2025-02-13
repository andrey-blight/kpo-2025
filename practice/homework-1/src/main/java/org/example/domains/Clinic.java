package org.example.domains;

public class Clinic {
    public boolean check_health(Animal animal) {
        return animal.getFoodConsumption() < 100;
    }
}
