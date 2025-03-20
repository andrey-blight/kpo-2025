package org.example.commands.operations;

import org.example.interfaces.Command;
import org.example.services.OperationService;

public class DeleteOperationCommand implements Command {
    private final OperationService operationService;
    private final Long id;

    public DeleteOperationCommand(OperationService operationService, Long id) {
        this.operationService = operationService;
        this.id = id;
    }

    @Override
    public void execute() {
        operationService.delete(id);
    }
}
