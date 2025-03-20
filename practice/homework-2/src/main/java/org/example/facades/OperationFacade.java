package org.example.facades;

import org.example.entities.Account;
import org.example.entities.Category;
import org.example.entities.Operation;
import org.example.factories.CommandOperationFactory;
import org.example.interfaces.Command;
import org.example.interfaces.ResultCommand;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class OperationFacade {
    private final CommandOperationFactory commandFactory;

    public OperationFacade(CommandOperationFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    public Operation createOperation(Account account, Category category, Long amount,
                                     LocalDate date, String description, Boolean with_runtime) {
        ResultCommand<Operation> command = this.commandFactory.createOperationCommand(account, category, amount,
                date, description, with_runtime);
        command.execute();
        return command.getResult();
    }

    public void deleteOperation(Long id, Boolean with_runtime) {
        Command command = this.commandFactory.deleteOperationCommand(id, with_runtime);
        command.execute();
    }

    public Operation updateOperation(Long id, Account account, Category category, Long amount,
                                     LocalDate date, String description, Boolean with_runtime) {
        ResultCommand<Operation> command = this.commandFactory.updateOperationCommand(id, account, category, amount,
                date, description, with_runtime);
        command.execute();
        return command.getResult();
    }

    public Operation getOperation(Long id, Boolean with_runtime) {
        ResultCommand<Operation> command = this.commandFactory.getOperationCommand(id, with_runtime);
        command.execute();
        return command.getResult();
    }
}
