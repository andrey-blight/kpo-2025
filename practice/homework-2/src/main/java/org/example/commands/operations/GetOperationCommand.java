package org.example.commands.operations;

import lombok.Getter;
import org.example.entities.Operation;
import org.example.interfaces.ResultCommand;
import org.example.services.OperationService;

public class GetOperationCommand implements ResultCommand<Operation> {
    private final OperationService operationService;
    private final Long id;

    @Getter
    private Operation result;

    public GetOperationCommand(OperationService operationService, Long id) {
        this.operationService = operationService;
        this.id = id;
    }

    @Override
    public void execute() {
        result = operationService.get(id);
    }
}
