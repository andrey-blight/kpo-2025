package org.example.commands.operations;

import lombok.Getter;
import org.example.entities.Account;
import org.example.entities.Category;
import org.example.entities.Operation;
import org.example.interfaces.ResultCommand;
import org.example.services.OperationService;

import java.time.LocalDate;

public class CreateOperationCommand implements ResultCommand<Operation> {
    private final OperationService operationService;
    private final Account account;
    private final Category category;
    private final Long amount;
    private final LocalDate date;
    private final String description;
    @Getter
    private Operation result;

    public CreateOperationCommand(OperationService operationService, Account account,
                                  Category category, Long amount, LocalDate date, String description) {
        this.operationService = operationService;
        this.account = account;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    @Override
    public void execute() {
        this.result = operationService.create(account, category, amount, date, description);
    }
}