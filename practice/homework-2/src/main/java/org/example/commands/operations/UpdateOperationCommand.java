package org.example.commands.operations;

import lombok.Getter;
import org.example.entities.Account;
import org.example.entities.Category;
import org.example.entities.Operation;
import org.example.interfaces.ResultCommand;
import org.example.services.OperationService;

import java.time.LocalDate;

public class UpdateOperationCommand implements ResultCommand<Operation> {
    private final OperationService operationService;
    private final Long id;
    private final Account account;
    private final Category category;
    private final Long amount;
    private final LocalDate date;
    private final String description;
    @Getter
    private Operation result;

    public UpdateOperationCommand(OperationService operationService, Long id, Account account,
                                  Category category, Long amount, LocalDate date, String description) {
        this.operationService = operationService;
        this.id = id;
        this.account = account;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    @Override
    public void execute() {
        this.result = operationService.update(id, account, category, amount, date, description);
    }
}
