package com.example.payment.service;

import com.example.payment.config.RabbitConfig;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentMessageListener {
    private final PaymentProcessingService paymentProcessingService;
    private final PaymentResultPublisher paymentResultPublisher;

    PaymentMessageListener(PaymentProcessingService paymentProcessingService,
                           PaymentResultPublisher paymentResultPublisher) {
        this.paymentProcessingService = paymentProcessingService;
        this.paymentResultPublisher = paymentResultPublisher;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void handleOrderCreated(String messagePayload) {
        System.out.println("Received message in payment service: " + messagePayload);

        boolean result = paymentProcessingService.handlePaymentEvent(messagePayload);

        String orderId = extractOrderId(messagePayload);

        paymentResultPublisher.sendResult(orderId, result);
    }

    private String extractOrderId(String payload) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = objectMapper.readValue(payload, new TypeReference<>() {
            });
            return (String) map.get("orderId");
        } catch (Exception e) {
            throw new RuntimeException("Invalid payload: " + payload);
        }
    }
}