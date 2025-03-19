package org.example.commands.accounts;

import lombok.Getter;
import org.example.entities.Account;
import org.example.interfaces.ResultCommand;
import org.example.services.AccountService;

public class CreateAccountCommand implements ResultCommand<Account> {
    private final AccountService accountService;
    private final String name;
    private final Long balance;
    @Getter
    private Account result;

    public CreateAccountCommand(AccountService accountService, String name, Long balance) {
        this.accountService = accountService;
        this.name = name;
        this.balance = balance;
    }

    @Override
    public void execute() {
        this.result = accountService.create(name, balance);
    }
}
