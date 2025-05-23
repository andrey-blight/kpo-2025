package com.example.analytics.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "statistics")
public class StatisticEntity {

    @Id
    private Long id;

    private int wordCount;

    private int charsCount;

    private String location;
}