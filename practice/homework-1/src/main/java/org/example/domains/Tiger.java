package org.example.domains;

public class Tiger extends Predator {
    public Tiger(int foodConsumption, String name) {
        super(foodConsumption, "Tiger " + name);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\ntype: Tiger\n";
    }
}
