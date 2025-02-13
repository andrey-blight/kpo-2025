package org.example.domains;

import org.example.interfaces.InterfaceAlive;

public class Animal implements InterfaceAlive {
    protected int foodConsumption;
    protected String name;

    public Animal(int foodConsumption, String name) {
        this.foodConsumption = foodConsumption;
        this.name = name;
    }

    @Override
    public int getFoodConsumption() {
        return foodConsumption;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "food Consumption: " + foodConsumption +
                "\nname: " + name;
    }
}
