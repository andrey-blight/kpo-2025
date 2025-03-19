package org.example.decorators;

import org.example.interfaces.Command;

public class RuntimeDecorator implements Command {
    private final Command command;

    public RuntimeDecorator(Command command) {
        this.command = command;
    }

    @Override
    public void execute() {
        long start = System.nanoTime();
        command.execute();
        long end = System.nanoTime();
        System.out.println("Execution time: " + (end - start) / 1_000_000.0 + " ms");
    }
}