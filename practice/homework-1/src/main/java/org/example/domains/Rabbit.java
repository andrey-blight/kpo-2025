package org.example.domains;

public class Rabbit extends Mammal {
    public Rabbit(int kindness, int foodConsumption, String name) {
        super(kindness, foodConsumption, "Rabbit " + name);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\ntype: Rabbit\n";
    }
}
