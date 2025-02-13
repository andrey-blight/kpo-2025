package org.example;

import org.example.fabrics.PredatorFabric;
import org.example.params.PredatorParams;
import org.example.services.ZooService;

public class Main {
    public static void main(String[] args) {
        var zoo = new ZooService();
        zoo.addAnimal(new PredatorFabric(), new PredatorParams("wolf", "Andrey", 3));

        zoo.print_info();
    }
}