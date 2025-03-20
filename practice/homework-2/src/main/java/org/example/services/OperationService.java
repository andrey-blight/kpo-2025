package org.example.services;

import org.example.entities.Account;
import org.example.entities.Category;
import org.example.entities.Operation;
import org.example.enums.CategoryType;
import org.example.interfaces.AccountRepository;
import org.example.interfaces.OperationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OperationService {
    private final OperationRepository operationRepository;
    private final AccountRepository accountRepository;

    private Account updateBalance(Operation operation, Boolean is_create) {
        // update balance
        Category category = operation.getCategory();
        Account account = operation.getAccount();

        if ((category.getCategoryType() == CategoryType.INCOME && is_create) ||
                (category.getCategoryType() == CategoryType.CONSUMPTION && !is_create)) {
            account.setBalance(account.getBalance() + operation.getAmount());
        } else {
            account.setBalance(account.getBalance() - operation.getAmount());
        }

        accountRepository.save(account);
        return account;
    }

    public OperationService(OperationRepository operationRepository, AccountRepository accountRepository) {
        this.operationRepository = operationRepository;
        this.accountRepository = accountRepository;
    }

    public Operation create(Account account, Category category, Long amount, LocalDate date, String description) {
        Operation operation = new Operation();
        operation.setAccount(account);
        operation.setCategory(category);
        operation.setAmount(amount);
        operation.setDate(date);
        operation.setDescription(description);

        // update balance
        account = this.updateBalance(operation, true);

        this.accountRepository.save(account);
        return operationRepository.save(operation);
    }

    public void delete(Long id) {
        Operation operation = this.get(id);

        // update balance
        this.updateBalance(operation, false);

        operationRepository.deleteById(id);
    }

    public Operation update(Long id, Account account, Category category, Long amount, LocalDate date, String description) {
        Operation operation = this.get(id);
        account = this.updateBalance(operation, false);
        operation.setAccount(account);
        operation.setCategory(category);
        operation.setAmount(amount);
        operation.setDate(date);
        operation.setDescription(description);

        this.updateBalance(operation, true);
        return operationRepository.save(operation);
    }

    public Operation get(Long id) {
        return operationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Операция не найден"));
    }
}