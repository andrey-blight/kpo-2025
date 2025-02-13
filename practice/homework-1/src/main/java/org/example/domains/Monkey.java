package org.example.domains;

public class Monkey extends Mammal {
    public Monkey(int kindness, int foodConsumption, String name) {
        super(kindness, foodConsumption, "Monkey " + name);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\ntype: Monkey\n";
    }
}
