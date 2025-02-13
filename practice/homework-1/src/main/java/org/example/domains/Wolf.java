package org.example.domains;

public class Wolf extends Predator {
    public Wolf(int foodConsumption, String name) {
        super(foodConsumption, "Wolf " + name);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\ntype: Wolf\n";
    }
}
