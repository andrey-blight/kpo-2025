package org.example.services;

import lombok.Getter;
import org.example.domains.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
@Component
public class ThingStorage {
    private final List<Thing> things = new ArrayList<>();

    public <T> void addThing(String type) {
        var number = things.size();
        if (Objects.equals(type, "computer")) {
            things.add(new Computer(number));
        } else if (Objects.equals(type, "table")) {
            things.add(new Table(number));
        } else {
            throw new IllegalArgumentException("Неизвестная вещь: " + type);
        }
    }

    public void print_info() {
        System.out.println("Total things: " + things.size() + "\n");
        System.out.println("All things:");
        things.stream().map(Thing::toString).forEach(System.out::println);
    }
}
