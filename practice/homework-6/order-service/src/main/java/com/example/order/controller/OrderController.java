package com.example.order.controller;


import com.example.order.dto.CreateOrderDto;
import com.example.order.entity.OrderEntity;
import com.example.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
}