package org.example.application.services;

import org.example.domain.repositories.AnimalRepositoryInterface;
import org.springframework.stereotype.Service;

@Service
public class ZooStatisticsService {

    private final AnimalRepositoryInterface animalRepository;

    public ZooStatisticsService(AnimalRepositoryInterface animalRepository) {
        this.animalRepository = animalRepository;
    }

    public long getStatistics() {
        return animalRepository.count();
    }
}