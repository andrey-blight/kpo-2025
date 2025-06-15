package com.example.payment.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE_NAME = "payment-exchange";

    // Входящее сообщение: заказ создан
    public static final String QUEUE_NAME = "payment-created-queue";
    public static final String ROUTING_KEY = "payment.created";

    // Исходящее сообщение: результат оплаты
    public static final String RESULT_QUEUE_NAME = "payment-result-queue";
    public static final String RESULT_ROUTING_KEY = "payment.result";

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue createdQueue() {
        return new Queue(QUEUE_NAME, true); // durable
    }

    @Bean
    public Queue resultQueue() {
        return new Queue(RESULT_QUEUE_NAME, true); // durable
    }

    @Bean
    public Binding createdBinding(Queue createdQueue, DirectExchange exchange) {
        return BindingBuilder.bind(createdQueue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public Binding resultBinding(Queue resultQueue, DirectExchange exchange) {
        return BindingBuilder.bind(resultQueue).to(exchange).with(RESULT_ROUTING_KEY);
    }
}