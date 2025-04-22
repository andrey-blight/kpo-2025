package org.example.domain.models;

import jakarta.persistence.*;
import lombok.*;
import org.example.domain.valueObjects.AnimalType;
import org.example.domain.valueObjects.FeedType;

import java.time.LocalDate;

@Entity
@Table(name = "animals")
@Data
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String species;
    private String name;
    private LocalDate birthDate;
    private String gender;
    private String status;

    @ManyToOne
    @JoinColumn(name = "feed_id", nullable = false)
    private FeedType favoriteFood;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private AnimalType type;

    @ManyToOne
    @JoinColumn(name = "enclosure_id", nullable = true)
    private Enclosure currentEnclosure;

    public String feed(FeedType food) {
        if (food.getId().equals(favoriteFood.getId())) {
            return name + " eating its favorite food: " + favoriteFood.getName();
        } else {
            return name + " is eating " + food.getName() + ", but prefers " + favoriteFood.getName();
        }
    }

    public String treat(String treatment) {
        if (status.equals("Healthy")) {
            return name + " already healthy ";
        }
        status = "Healthy";
        return name + " treated with " + treatment;
    }

    public void moveToEnclosure(Enclosure newEnclosure) {
        if (currentEnclosure.equals(newEnclosure)) {
            throw new RuntimeException(name + " is already in the " + currentEnclosure.getId() + " enclosure");
        }
        if (newEnclosure.getType() != type) {
            throw new RuntimeException(name + " can't be live in enclosure with type " + newEnclosure.getType().getName());
        }
        if (newEnclosure.getCount().equals(newEnclosure.getMaxCount())) {
            throw new RuntimeException("Enclosure already has max count of animals");
        }

        currentEnclosure.deleteAnimal(this);
        newEnclosure.addAnimal(this);
    }
}