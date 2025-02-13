package org.example.domains;

import org.springframework.stereotype.Component;

@Component
public class Clinic {
    public boolean check_health(Animal animal) {
        return animal.getFoodConsumption() < 100;
    }
}
