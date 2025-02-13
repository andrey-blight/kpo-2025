package org.example.domains;

import lombok.Getter;

@Getter
public class Mammal extends Animal {
    protected int kindness;

    public Mammal(int kindness, int foodConsumption, String name) {
        super(foodConsumption, name);
        this.kindness = kindness;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nkindness: " + this.getKindness();
    }
}
