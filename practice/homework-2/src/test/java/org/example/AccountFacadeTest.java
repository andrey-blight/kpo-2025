package org.example;

import org.example.entities.Account;
import org.example.facades.AccountFacade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AccountFacadeTest {
    @Autowired
    private AccountFacade facade;

    @Test
    @DisplayName("Тест создания аккаунта")
    public void testCreateAccount() {

        Account account = facade.createAccount("my_acc", 0L, false);

        assertEquals(0L, account.getBalance());
        assertEquals("my_acc", account.getName());
    }

    @Test
    @DisplayName("Тест создания аккаунта c отображением рантайма")
    public void testCreateAccountWithRuntime() {
        System.out.println("Создание аккаунта за:");
        facade.createAccount("my_acc", 0L, true);
    }

    @Test
    @DisplayName("Тест удаления несуществующего аккаунта")
    public void testDeleteNonExistingAccount() {
        assertThrows(RuntimeException.class, () -> facade.deleteAccount(0L, false));
    }

    @Test
    @DisplayName("Тест удаления аккаунта")
    public void testDeleteAccount() {
        Account account = facade.createAccount("my_acc", 0L, false);
        facade.deleteAccount(account.getId(), false);

        assertThrows(RuntimeException.class, () -> facade.deleteAccount(account.getId(), false));
    }

    @Test
    @DisplayName("Тест удаления аккаунта с рантаймом")
    public void testDeleteAccountWithRuntime() {
        Account account = facade.createAccount("my_acc", 0L, false);
        System.out.println("Удаление аккаунта за:");
        facade.deleteAccount(account.getId(), true);
    }

    @Test
    @DisplayName("Тест изменения несуществующего аккаунта")
    public void testUpdateNonExistingAccount() {
        assertThrows(RuntimeException.class, () -> facade.updateAccount(0L, "new_name", 1L, false));
    }

    @Test
    @DisplayName("Тест изменения аккаунта")
    public void testUpdateAccount() {
        Account account = facade.createAccount("my_acc", 0L, false);
        Account edited = facade.updateAccount(account.getId(), "new_acc", 1L, false);

        assertEquals(account.getId(), edited.getId());
        assertEquals(1L, edited.getBalance());
        assertEquals("new_acc", edited.getName());
    }

    @Test
    @DisplayName("Тест изменения аккаунта с рантаймом")
    public void testUpdateAccountWithRuntime() {
        Account account = facade.createAccount("my_acc", 0L, false);
        System.out.println("Изменение аккаунта за:");
        facade.updateAccount(account.getId(), "new_acc", 1L, true);
    }

    @Test
    @DisplayName("Тест получения несуществующего аккаунта")
    public void testGetNonExistingAccount() {
        assertThrows(RuntimeException.class, () -> facade.getAccount(0L, false));
    }

    @Test
    @DisplayName("Тест получения аккаунта")
    public void testGetAccount() {
        Account account = facade.createAccount("my_acc", 0L, false);
        Account geted_account = facade.getAccount(account.getId(), false);

        assertEquals(account.getId(), geted_account.getId());
        assertEquals(account.getBalance(), geted_account.getBalance());
        assertEquals(account.getName(), geted_account.getName());
    }

    @Test
    @DisplayName("Тест получения аккаунта с рантаймом")
    public void testGetAccountWithRuntime() {
        Account account = facade.createAccount("my_acc", 0L, false);
        System.out.println("Получение аккаунта за:");
        facade.getAccount(account.getId(), true);
    }

}

