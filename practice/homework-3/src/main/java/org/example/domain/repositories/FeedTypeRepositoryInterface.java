package org.example.domain.repositories;

import org.example.domain.valueObjects.FeedType;

import java.util.List;
import java.util.Optional;

public interface FeedTypeRepositoryInterface {

    Optional<FeedType> findById(Long id);

    FeedType save(FeedType type);

    void delete(FeedType animal);

    long count();

    List<FeedType> findAll();
}