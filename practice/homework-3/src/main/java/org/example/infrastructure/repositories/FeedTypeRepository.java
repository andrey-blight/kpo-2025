package org.example.infrastructure.repositories;

import org.example.domain.repositories.FeedTypeRepositoryInterface;
import org.example.domain.valueObjects.FeedType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedTypeRepository extends FeedTypeRepositoryInterface, JpaRepository<FeedType, Long> {
}