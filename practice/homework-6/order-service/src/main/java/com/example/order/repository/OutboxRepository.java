package com.example.order.repository;

import com.example.order.entity.OutboxMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OutboxRepository extends JpaRepository<OutboxMessage, UUID> {
}