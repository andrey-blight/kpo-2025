package org.example.factories;

import org.example.commands.accounts.CreateAccountCommand;
import org.example.commands.accounts.DeleteAccountCommand;
import org.example.commands.accounts.GetAccountCommand;
import org.example.commands.accounts.UpdateAccountCommand;
import org.example.decorators.RuntimeDecorator;
import org.example.decorators.RuntimeResultDecorator;
import org.example.entities.Account;
import org.example.interfaces.Command;
import org.example.interfaces.ResultCommand;
import org.example.services.AccountService;
import org.springframework.stereotype.Component;

@Component
public class CommandAccountFactory {
    private final AccountService accountService;

    public CommandAccountFactory(AccountService accountRepository) {
        this.accountService = accountRepository;
    }

    public ResultCommand<Account> createAccountCommand(String name, Long balance, Boolean with_runtime) {
        ResultCommand<Account> command = new CreateAccountCommand(accountService, name, balance);

        if (with_runtime) {
            return new RuntimeResultDecorator<>(command);
        }
        return command;
    }

    public Command deleteAccountCommand(Long id, Boolean with_runtime) {
        Command command = new DeleteAccountCommand(accountService, id);

        if (with_runtime) {
            return new RuntimeDecorator(command);
        }
        return command;
    }

    public ResultCommand<Account> updateAccountCommand(Long id, String name, Long balance, Boolean with_runtime) {
        ResultCommand<Account> command = new UpdateAccountCommand(accountService, id, name, balance);

        if (with_runtime) {
            return new RuntimeResultDecorator<>(command);
        }
        return command;
    }

    public ResultCommand<Account> getAccountCommand(Long id, Boolean with_runtime) {
        ResultCommand<Account> command = new GetAccountCommand(accountService, id);

        if (with_runtime) {
            return new RuntimeResultDecorator<>(command);
        }
        return command;
    }
}
