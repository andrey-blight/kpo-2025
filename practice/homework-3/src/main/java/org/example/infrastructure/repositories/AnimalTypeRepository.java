package org.example.infrastructure.repositories;

import org.example.domain.repositories.AnimalTypeRepositoryInterface;
import org.example.domain.valueObjects.AnimalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalTypeRepository extends AnimalTypeRepositoryInterface, JpaRepository<AnimalType, Long> {
}