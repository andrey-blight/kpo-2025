package org.example.domain.repositories;

import org.example.domain.models.FeedingSchedule;

import java.time.LocalDate;
import java.util.Optional;

public interface FeedingScheduleRepositoryInterface {

    Optional<FeedingSchedule> findById(Long id);

    FeedingSchedule save(FeedingSchedule feedingSchedule);

    void delete(FeedingSchedule feedingSchedule);

    Optional<FeedingSchedule> findNextFeedingSchedule(Long animalId, LocalDate date);
}