package org.example.domains;

public class Table extends Thing {
    public Table(int number) {
        super(number);
    }

    @Override
    public String toString() {
        return super.toString() + "\ntype: Table\n";
    }

}