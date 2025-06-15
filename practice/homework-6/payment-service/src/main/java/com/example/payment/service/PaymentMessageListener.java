package com.example.payment.service;

import com.example.payment.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentMessageListener {

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void handleOrderCreated(String messagePayload) {
        System.out.println("Received message in payment service: " + messagePayload);

        // Здесь можно распарсить JSON payload и запустить логику оплаты
        // Например, использовать ObjectMapper для десериализации
        // и затем вызвать сервис, который проведёт оплату

        // Пример:
        // OrderPaymentRequest request = objectMapper.readValue(messagePayload, OrderPaymentRequest.class);
        // paymentService.processPayment(request);
    }
}