package org.example.infrastructure.repositories;

import org.example.domain.models.Animal;
import org.example.domain.repositories.AnimalRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends AnimalRepositoryInterface, JpaRepository<Animal, Long> {
}