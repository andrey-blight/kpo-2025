package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

// table accounts
@Entity
@Table(name = "accounts")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long balance;
}
