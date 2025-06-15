package com.example.order.controller;

import com.example.order.dto.CreateOrderDto;
import com.example.order.entity.OrderEntity;
import com.example.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/order")
    public ResponseEntity<OrderEntity> createAccount(@RequestBody CreateOrderDto request) {
        OrderEntity result = orderService.createOrder(request.getUserId(), request.getAmount());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/order/{order_id}")
    public ResponseEntity<OrderEntity> getImageById(@PathVariable UUID order_id) {
        OrderEntity response = orderService.getOrder(order_id);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<List<OrderEntity>> getImageById(@PathVariable int user_id) {
        List<OrderEntity> response = orderService.getOrdersByUserId(user_id);

        return ResponseEntity.ok().body(response);
    }
}