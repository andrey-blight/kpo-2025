package com.example.payment.service;

import com.example.payment.entity.AccountEntity;
import com.example.payment.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final AccountRepository accountRepository;

    public PaymentService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public AccountEntity createAccount(int userId) {
        if (accountRepository.findById(userId).isPresent()) {
            return null;
        }

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUserId(userId);
        accountEntity.setBalance(0);
        return accountRepository.save(accountEntity);
    }

    public AccountEntity getAccount(int userId) {
        return accountRepository.findById(userId).orElse(null);
    }
}