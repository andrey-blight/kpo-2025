package org.example.infrastructure.repositories;

import org.example.domain.models.Enclosure;
import org.example.domain.repositories.EnclosureRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnclosureRepository extends EnclosureRepositoryInterface, JpaRepository<Enclosure, Long> {
}
