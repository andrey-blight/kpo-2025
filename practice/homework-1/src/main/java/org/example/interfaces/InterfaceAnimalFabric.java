package org.example.interfaces;

import org.example.domains.Animal;

public interface InterfaceAnimalFabric<T> {

    Animal createAnimal(T params);
}
