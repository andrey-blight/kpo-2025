package org.example.application.events;

import java.time.LocalDate;

public record FeedingTimeEvent(Long animalId, LocalDate feedingTime) {
}