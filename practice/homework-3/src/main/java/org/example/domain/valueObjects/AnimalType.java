package org.example.domain.valueObjects;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "animal_types")
@Data
public class AnimalType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}