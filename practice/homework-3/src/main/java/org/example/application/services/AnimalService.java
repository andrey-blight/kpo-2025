package org.example.application.services;

import org.example.domain.models.Animal;
import org.example.domain.repositories.AnimalRepositoryInterface;
import org.example.domain.repositories.AnimalTypeRepositoryInterface;
import org.example.domain.repositories.FeedTypeRepositoryInterface;
import org.example.domain.valueObjects.AnimalType;
import org.example.domain.valueObjects.FeedType;
import org.example.infrastructure.repositories.AnimalTypeRepository;
import org.example.infrastructure.repositories.FeedTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    private final AnimalRepositoryInterface animalRepository;
    private final AnimalTypeRepositoryInterface animalTypeRepository;
    private final FeedTypeRepositoryInterface feedTypeRepository;

    @Autowired
    public AnimalService(AnimalRepositoryInterface animalRepository, AnimalTypeRepositoryInterface animalTypeRepository, FeedTypeRepositoryInterface feedTypeRepository) {
        this.animalRepository = animalRepository;
        this.animalTypeRepository = animalTypeRepository;
        this.feedTypeRepository = feedTypeRepository;
    }

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    public Optional<Animal> getAnimalById(Long animalId) {
        return animalRepository.findById(animalId);
    }

    @Transactional
    public Animal createAnimal(Animal animal) {
        if (animal.getFavoriteFood() == null) {
            throw new IllegalArgumentException("Animal food cannot be null");
        } else {
            Long feedTypeId = animal.getFavoriteFood().getId();
            Optional<FeedType> type = feedTypeRepository.findById(feedTypeId);

            if (type.isEmpty()) {
                FeedType feedType = feedTypeRepository.save(new FeedType(animal.getFavoriteFood().getName()));
                animal.setFavoriteFood(feedType);
            }
        }

        if (animal.getType() == null) {
            throw new IllegalArgumentException("Animal type cannot be null");
        } else {
            Long animalTypeId = animal.getType().getId();
            Optional<AnimalType> type = animalTypeRepository.findById(animalTypeId);

            if (type.isEmpty()) {
                AnimalType animalType = animalTypeRepository.save(new AnimalType(animal.getType().getName()));
                animal.setType(animalType);
            }
        }

        return animalRepository.save(animal);
    }

    public Animal updateAnimal(Long animalId, Animal updatedAnimal) {
        Animal existingAnimal = animalRepository.findById(animalId)
                .orElseThrow(() -> new RuntimeException("Animal not found"));

        existingAnimal.setSpecies(updatedAnimal.getSpecies());
        existingAnimal.setName(updatedAnimal.getName());
        existingAnimal.setBirthDate(updatedAnimal.getBirthDate());
        existingAnimal.setGender(updatedAnimal.getGender());
        existingAnimal.setStatus(updatedAnimal.getStatus());
        existingAnimal.setFavoriteFood(updatedAnimal.getFavoriteFood());
        existingAnimal.setType(updatedAnimal.getType());
        existingAnimal.setCurrentEnclosure(updatedAnimal.getCurrentEnclosure());

        return animalRepository.save(existingAnimal);
    }

    public void deleteAnimal(Long animalId) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new RuntimeException("Animal not found"));

        animalRepository.delete(animal);
    }
}