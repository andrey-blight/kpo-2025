package com.example.payment.repository;

import com.example.payment.entity.InboxMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InboxRepository extends JpaRepository<InboxMessage, UUID> {
}