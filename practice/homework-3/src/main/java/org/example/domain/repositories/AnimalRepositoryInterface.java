package org.example.domain.repositories;

import org.example.domain.models.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalRepositoryInterface {

    Optional<Animal> findById(Long id);

    Animal save(Animal animal);

    void delete(Animal animal);

    long count();

    List<Animal> findAll();
}