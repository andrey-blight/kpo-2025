package org.example.infrastructure.repositories;

import org.example.domain.models.FeedingSchedule;
import org.example.domain.repositories.FeedingScheduleRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface FeedingScheduleRepository extends FeedingScheduleRepositoryInterface, JpaRepository<FeedingSchedule, Long> {
    @Query("SELECT f FROM FeedingSchedule f WHERE f.animal.id = :animalId AND f.feedingTime >= :date ORDER BY f.feedingTime ASC LIMIT 1")
    Optional<FeedingSchedule> findNextFeedingSchedule(Long animalId, LocalDate date);
}
