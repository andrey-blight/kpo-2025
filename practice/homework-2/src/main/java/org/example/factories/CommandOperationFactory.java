package org.example.factories;

import org.example.commands.operations.CreateOperationCommand;
import org.example.commands.operations.DeleteOperationCommand;
import org.example.commands.operations.GetOperationCommand;
import org.example.commands.operations.UpdateOperationCommand;
import org.example.decorators.RuntimeDecorator;
import org.example.decorators.RuntimeResultDecorator;
import org.example.entities.Account;
import org.example.entities.Category;
import org.example.entities.Operation;
import org.example.interfaces.Command;
import org.example.interfaces.ResultCommand;
import org.example.services.OperationService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CommandOperationFactory {
    private final OperationService operationService;

    public CommandOperationFactory(OperationService operationService) {
        this.operationService = operationService;
    }

    private Command getFinalCommand(Command command, Boolean with_runtime) {
        if (with_runtime) {
            return new RuntimeDecorator(command);
        }
        return command;
    }

    private ResultCommand<Operation> getFinalCommand(ResultCommand<Operation> command, Boolean with_runtime) {
        if (with_runtime) {
            return new RuntimeResultDecorator<>(command);
        }
        return command;
    }

    public ResultCommand<Operation> createOperationCommand(Account account, Category category, Long amount,
                                                           LocalDate date, String description, Boolean with_runtime) {
        ResultCommand<Operation> command = new CreateOperationCommand(operationService, account,
                category, amount, date, description);

        return getFinalCommand(command, with_runtime);
    }

    public Command deleteOperationCommand(Long id, Boolean with_runtime) {
        Command command = new DeleteOperationCommand(operationService, id);

        return getFinalCommand(command, with_runtime);
    }

    public ResultCommand<Operation> updateOperationCommand(Long id, Account account, Category category, Long amount,
                                                           LocalDate date, String description, Boolean with_runtime) {
        ResultCommand<Operation> command = new UpdateOperationCommand(operationService, id, account, category,
                amount, date, description);

        return getFinalCommand(command, with_runtime);
    }

    public ResultCommand<Operation> getOperationCommand(Long id, Boolean with_runtime) {
        ResultCommand<Operation> command = new GetOperationCommand(operationService, id);

        return getFinalCommand(command, with_runtime);
    }
}
