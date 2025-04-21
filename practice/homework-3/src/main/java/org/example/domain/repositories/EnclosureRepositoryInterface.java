package org.example.domain.repositories;

import org.example.domain.models.Enclosure;

import java.util.Optional;

public interface EnclosureRepositoryInterface {

    Optional<Enclosure> findById(Long id);

    Enclosure save(Enclosure enclosure);

    void delete(Enclosure enclosure);
}