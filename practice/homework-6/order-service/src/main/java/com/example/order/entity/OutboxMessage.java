package com.example.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "outbox")
public class OutboxMessage {
    @Id
    private UUID id;

    private String eventType;

    @Lob
    private String payload;

    private LocalDateTime createdAt;

    private boolean processed;
}