package com.example.order.service;

import com.example.order.config.RabbitConfig;
import com.example.order.enums.OrderStatus;
import com.example.order.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class PaymentResultListener {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final OrderRepository orderRepository;

    public PaymentResultListener(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @RabbitListener(queues = RabbitConfig.RESULT_QUEUE_NAME)
    public void handlePaymentResult(String message) {
        try {
            System.out.println("Received message in payment service: " + message);
            Map<String, Object> payload = objectMapper.readValue(
                    message,
                    new com.fasterxml.jackson.core.type.TypeReference<>() {
                    }
            );

            String orderIdStr = (String) payload.get("orderId");
            boolean success = (Boolean) payload.get("success");

            UUID orderId = UUID.fromString(orderIdStr);

            orderRepository.findById(orderId).ifPresent(order -> {
                order.setStatus(success ? OrderStatus.SUCCEEDED : OrderStatus.FAILED);
                orderRepository.save(order);
            });

        } catch (Exception e) {
            System.err.println("❌ Failed to process payment result: " + message);
        }
    }
}