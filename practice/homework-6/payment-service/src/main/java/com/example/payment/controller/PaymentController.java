package com.example.payment.controller;

import com.example.payment.dto.CreateAccountDto;
import com.example.payment.dto.CreateIncomeDto;
import com.example.payment.entity.AccountEntity;
import com.example.payment.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @PostMapping("/account")
    public ResponseEntity<AccountEntity> createAccount(@RequestBody CreateAccountDto request) {
        AccountEntity result = paymentService.createAccount(request.getUserId());

        if (result == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account already exist for user " + request.getUserId());
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/account/{user_id}")
    public ResponseEntity<AccountEntity> getImageById(@PathVariable int user_id) {
        AccountEntity response = paymentService.getAccount(user_id);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/account/income")
    public ResponseEntity<AccountEntity> addIncome(@RequestBody CreateIncomeDto request) {
        AccountEntity result = paymentService.addIncome(request.getUserId(), request.getAmount());

        if (result == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result);
    }
}