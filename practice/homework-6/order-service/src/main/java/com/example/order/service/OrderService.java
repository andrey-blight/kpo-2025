package com.example.order.service;

import com.example.order.entity.OrderEntity;
import com.example.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

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
}