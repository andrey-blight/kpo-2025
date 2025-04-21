package org.example.domain.models;

import lombok.*;

import jakarta.persistence.*;
import org.example.domain.valueObjects.AnimalType;

import java.util.List;


@Entity
@Table(name = "enclosures")
@Data
public class Enclosure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer size;
    private Integer count;
    private Integer maxCount;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private AnimalType type;

    @OneToMany(mappedBy = "currentEnclosure")
    private List<Animal> animals;

    public void addAnimal(Animal animal) {
        if (animal.getCurrentEnclosure().equals(this)) {
            throw new RuntimeException(animal.getName() + " is already in the " + id + " enclosure");
        }
        if (type != animal.getCurrentEnclosure().getType()) {
            throw new RuntimeException(animal.getName() + " can't be live in enclosure with type " + type.getName());
        }
        if (count.equals(maxCount)) {
            throw new RuntimeException("Enclosure already has max count of animals");
        }
        animals.add(animal);
        animal.setCurrentEnclosure(this);
    }

    public void deleteAnimal(Animal animal) {
        if (!animals.contains(animal)) {
            throw new RuntimeException(animal.getName() + " is not in the " + id + " enclosure");
        }

        animals.remove(animal);
        count--;
        animal.setCurrentEnclosure(null);
    }

    public String clear() {
        return "Clear enclosure " + id;
    }
}