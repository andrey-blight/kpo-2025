package org.example.application.services;

import org.example.application.events.FeedingTimeEvent;
import org.example.application.publisher.EventPublisher;
import org.example.domain.models.Animal;
import org.example.domain.models.FeedingSchedule;
import org.example.domain.repositories.AnimalRepositoryInterface;
import org.example.domain.repositories.FeedingScheduleRepositoryInterface;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class FeedingOrganizationService {

    private final AnimalRepositoryInterface animalRepository;
    private final FeedingScheduleRepositoryInterface feedingScheduleRepository;
    private final EventPublisher eventPublisher;

    public FeedingOrganizationService(AnimalRepositoryInterface animalRepository, FeedingScheduleRepositoryInterface feedingScheduleRepository, EventPublisher eventPublisher) {
        this.animalRepository = animalRepository;
        this.feedingScheduleRepository = feedingScheduleRepository;
        this.eventPublisher = eventPublisher;
    }

    public String feedAnimal(Long animalId) {
        Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new RuntimeException("Animal not found"));

        Optional<FeedingSchedule> feedingScheduleOptional = feedingScheduleRepository.findNextFeedingSchedule(animalId, LocalDate.now());

        if (feedingScheduleOptional.isPresent()) {
            FeedingSchedule feedingSchedule = feedingScheduleOptional.get();

            String feedResult = animal.feed(feedingSchedule.getFeedType());
            feedingScheduleRepository.delete(feedingSchedule);

            FeedingTimeEvent event = new FeedingTimeEvent(animalId, feedingSchedule.getFeedingTime());
            eventPublisher.publish(event);
            return feedResult;
        } else {
            return "No feeding schedule found for today";
        }
    }
}