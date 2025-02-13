package org.example.domains;

public class Computer extends Thing {
    public Computer(int number) {
        super(number);
    }

    @Override
    public String toString() {
        return super.toString() + "\ntype: Computer\n";
    }

}
