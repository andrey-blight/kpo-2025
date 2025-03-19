package org.example.interfaces;

public interface ResultCommand<T> {
    void execute();

    T getResult();
}
