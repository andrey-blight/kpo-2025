package org.example.commands.accounts;

import lombok.Getter;
import org.example.entities.Account;
import org.example.interfaces.ResultCommand;
import org.example.services.AccountService;

public class UpdateAccountCommand implements ResultCommand<Account> {
    private final AccountService accountService;
    private final Long id;
    private final String name;
    private final Long balance;
    @Getter
    private Account result;

    public UpdateAccountCommand(AccountService accountService, Long id, String name, Long balance) {
        this.accountService = accountService;
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    @Override
    public void execute() {
        this.result = accountService.update(id, name, balance);
    }
}
