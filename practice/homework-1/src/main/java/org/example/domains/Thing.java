package org.example.domains;

import org.example.interfaces.InterfaceInventory;

public class Thing implements InterfaceInventory {
    protected int number;

    Thing(int number) {
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "number: " + getNumber();
    }
}
