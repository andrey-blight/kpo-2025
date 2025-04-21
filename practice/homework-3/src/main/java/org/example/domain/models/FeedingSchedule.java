package org.example.domain.models;

import jakarta.persistence.*;
import lombok.Data;
import org.example.domain.valueObjects.FeedType;

import java.time.LocalDate;


@Entity
@Table(name = "feeding_schdule")
@Data
public class FeedingSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate feedingTime;

    @ManyToOne
    @JoinColumn(name = "feed_id")
    private FeedType feedType;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    public String FeedAnimal() {
        return animal.feed(feedType);
    }
}