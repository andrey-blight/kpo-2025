package org.example.domain.valueObjects;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "feed_types")
@Data
public class FeedType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public FeedType(String name) {
        this.name = name;
    }

    public FeedType() {
    }
}