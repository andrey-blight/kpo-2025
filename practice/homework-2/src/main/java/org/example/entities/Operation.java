package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

// table operations
@Entity
@Table(name = "operations")
@Data
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    private Long amount;

    private LocalDate date;

    private String description;
}
