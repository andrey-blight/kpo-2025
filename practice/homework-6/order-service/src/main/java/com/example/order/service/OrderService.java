package com.example.order.service;

import com.example.order.entity.OrderEntity;
import com.example.order.entity.OutboxMessage;
import com.example.order.enums.OrderStatus;
import com.example.order.repository.OrderRepository;
import com.example.order.repository.OutboxRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OutboxRepository outboxRepository;
    private final MessagePublisher messagePublisher;
    private final ObjectMapper objectMapper;

    public OrderService(OrderRepository orderRepository, OutboxRepository outboxRepository,
                        MessagePublisher messagePublisher, ObjectMapper objectMapper) {
        this.orderRepository = orderRepository;
        this.outboxRepository = outboxRepository;
        this.messagePublisher = messagePublisher;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public OrderEntity createOrder(int userId, float amount) {
        try {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setUserId(userId);
            orderEntity.setAmount(amount);
            orderEntity.setStatus(OrderStatus.PROCESSING);

            orderEntity = orderRepository.save(orderEntity);

            String payload = objectMapper.writeValueAsString(Map.of(
                    "orderId", orderEntity.getId().toString(),
                    "userId", userId,
                    "amount", amount
            ));

            OutboxMessage message = new OutboxMessage();
            message.setId(UUID.randomUUID());
            message.setEventType("ORDER_CREATED");
            message.setPayload(payload);
            message.setCreatedAt(LocalDateTime.now());
            message.setProcessed(false);
            outboxRepository.save(message);

            messagePublisher.sendOrderCreatedEvent(payload);

            return orderEntity;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize order payload", e);
        }
    }

    public OrderEntity getOrder(UUID orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public List<OrderEntity> getOrdersByUserId(int userId) {
        return orderRepository.findByUserId(userId);
    }
}