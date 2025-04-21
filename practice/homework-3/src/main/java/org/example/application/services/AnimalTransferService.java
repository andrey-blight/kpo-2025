package org.example.application.services;

import org.example.application.publisher.EventPublisher;
import org.example.domain.models.Animal;
import org.example.domain.models.Enclosure;
import org.example.application.events.AnimalMovedEvent;
import org.example.domain.repositories.AnimalRepositoryInterface;
import org.example.domain.repositories.EnclosureRepositoryInterface;
import org.springframework.stereotype.Service;

@Service
public class AnimalTransferService {

    private final AnimalRepositoryInterface animalRepository;
    private final EnclosureRepositoryInterface enclosureRepository;
    private final EventPublisher eventPublisher;

    public AnimalTransferService(AnimalRepositoryInterface animalRepository, EnclosureRepositoryInterface enclosureRepository, EventPublisher eventPublisher) {
        this.animalRepository = animalRepository;
        this.enclosureRepository = enclosureRepository;
        this.eventPublisher = eventPublisher;
    }

    public Boolean moveAnimal(Long animalId, Long newEnclosureId) {
        Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new RuntimeException("Animal not found"));
        Enclosure newEnclosure = enclosureRepository.findById(newEnclosureId).orElseThrow(() -> new RuntimeException("Enclosure not found"));

        try {
            animal.moveToEnclosure(newEnclosure);
            AnimalMovedEvent event = new AnimalMovedEvent(animal.getId(), newEnclosure.getId());
            eventPublisher.publish(event);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
}