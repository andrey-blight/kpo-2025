package org.example.commands.accounts;

import lombok.Getter;
import org.example.entities.Account;
import org.example.interfaces.ResultCommand;
import org.example.services.AccountService;

public class GetAccountCommand implements ResultCommand<Account> {
    private final AccountService accountService;
    private final Long id;
    @Getter
    private Account result;

    public GetAccountCommand(AccountService accountService, Long id) {
        this.accountService = accountService;
        this.id = id;
    }

    @Override
    public void execute() {
        this.result = accountService.get(id);
    }
}
