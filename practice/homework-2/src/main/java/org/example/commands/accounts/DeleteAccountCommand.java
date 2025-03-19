package org.example.commands.accounts;

import org.example.interfaces.Command;
import org.example.services.AccountService;

public class DeleteAccountCommand implements Command {
    private final AccountService accountService;
    private final Long id;

    public DeleteAccountCommand(AccountService accountService, Long id) {
        this.accountService = accountService;
        this.id = id;
    }

    @Override
    public void execute() {
        accountService.delete(id);
    }
}
