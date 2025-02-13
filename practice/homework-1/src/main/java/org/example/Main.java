package org.example;

import org.example.fabrics.PredatorFabric;
import org.example.params.PredatorParams;
import org.example.services.ZooService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        var zoo = new ZooService();
        zoo.addAnimal(new PredatorFabric(), new PredatorParams("wolf", "Andrey", 3));

        zoo.print_info();
    }
}