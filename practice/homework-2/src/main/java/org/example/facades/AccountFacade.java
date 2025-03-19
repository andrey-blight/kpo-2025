package org.example.facades;

import org.example.entities.Account;
import org.example.factories.CommandAccountFactory;
import org.example.interfaces.Command;
import org.example.interfaces.ResultCommand;
import org.springframework.stereotype.Component;

@Component
public class AccountFacade {
    private final CommandAccountFactory commandFactory;

    public AccountFacade(CommandAccountFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    public Account createAccount(String name, Long balance, Boolean with_runtime) {
        ResultCommand<Account> command = this.commandFactory.createAccountCommand(name, balance, with_runtime);
        command.execute();
        return command.getResult();
    }

    public void deleteAccount(Long id, Boolean with_runtime) {
        Command command = this.commandFactory.deleteAccountCommand(id, with_runtime);
        command.execute();
    }

    public Account updateAccount(Long id, String newName, Long newBalance, Boolean with_runtime) {
        ResultCommand<Account> command = this.commandFactory.updateAccountCommand(id, newName, newBalance, with_runtime);
        command.execute();
        return command.getResult();
    }

    public Account getAccount(Long id, Boolean with_runtime) {
        ResultCommand<Account> command = this.commandFactory.getAccountCommand(id, with_runtime);
        command.execute();
        return command.getResult();
    }
}
