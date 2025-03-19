package org.example.decorators;

import org.example.interfaces.ResultCommand;

public class RuntimeResultDecorator<T> implements ResultCommand<T> {
    private final ResultCommand<T> command;

    public RuntimeResultDecorator(ResultCommand<T> command) {
        this.command = command;
    }

    @Override
    public void execute() {
        long start = System.nanoTime();
        command.execute();
        long end = System.nanoTime();
        System.out.println("Execution time: " + (end - start) / 1_000_000.0 + " ms");
    }

    @Override
    public T getResult() {
        return command.getResult();
    }
}
