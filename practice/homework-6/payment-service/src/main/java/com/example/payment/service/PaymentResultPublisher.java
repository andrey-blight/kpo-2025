package com.example.payment.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import com.example.payment.config.RabbitConfig;

@Component
public class PaymentResultPublisher {

    private final RabbitTemplate rabbitTemplate;

    public PaymentResultPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendResult(String orderId, boolean success) {
        String message = String.format("{\"orderId\":\"%s\", \"success\":%b}", orderId, success);
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE_NAME,
                RabbitConfig.RESULT_ROUTING_KEY,
                message
        );
    }
}