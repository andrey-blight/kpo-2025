package com.example.order.service;

import com.example.order.entity.OrderEntity;
import com.example.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderEntity createOrder(int userId, float amount) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserId(userId);
        orderEntity.setAmount(amount);
        return orderRepository.save(orderEntity);
    }

    public OrderEntity getOrder(UUID orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public List<OrderEntity> getOrdersByUserId(int userId) {
        return orderRepository.findByUserId(userId);
    }
}