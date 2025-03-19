package org.example.services;

import org.example.entities.Account;
import org.example.interfaces.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account create(String name, Long balance) {
        Account account = new Account();
        account.setName(name);
        account.setBalance(balance);
        return accountRepository.save(account);
    }

    public void delete(Long id) {
        this.get(id); // check that category with such id exist
        accountRepository.deleteById(id);
    }

    public Account update(Long id, String newName, Long newBalance) {
        Account account = this.get(id);
        account.setName(newName);
        account.setBalance(newBalance);
        return accountRepository.save(account);
    }

    public Account get(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Аккаунт не найден"));
    }
}