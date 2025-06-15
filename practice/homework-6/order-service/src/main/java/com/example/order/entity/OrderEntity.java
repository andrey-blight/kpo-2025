package com.example.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue
    @GenericGenerator(name = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "user_id")
    private int userId;

    @Column(nullable = false)
    private float amount;
}