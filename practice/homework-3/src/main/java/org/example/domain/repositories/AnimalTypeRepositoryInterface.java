package org.example.domain.repositories;

import org.example.domain.valueObjects.AnimalType;

import java.util.List;
import java.util.Optional;

public interface AnimalTypeRepositoryInterface {

    Optional<AnimalType> findById(Long id);

    AnimalType save(AnimalType type);

    void delete(AnimalType animal);

    long count();

    List<AnimalType> findAll();
}