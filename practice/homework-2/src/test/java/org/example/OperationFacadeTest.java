package org.example;

import org.example.entities.Account;
import org.example.entities.Category;
import org.example.entities.Operation;
import org.example.enums.CategoryType;
import org.example.facades.AccountFacade;
import org.example.facades.CategoryFacade;
import org.example.facades.OperationFacade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class OperationFacadeTest {
    @Autowired
    private OperationFacade facade;
    @Autowired
    private AccountFacade accountFacade;
    @Autowired
    private CategoryFacade categoryFacade;

    @Test
    @DisplayName("Тест создания операции")
    public void testCreateOperation() {
        Account account = accountFacade.createAccount("my_acc", 0L, false);
        Category category = categoryFacade.createCategory("Wage", CategoryType.INCOME, false);
        Operation operation = facade.createOperation(account, category, 100L,
                LocalDate.now(), "зп", false);

        assertEquals(100L, account.getBalance());
        assertEquals(100L, operation.getAmount());
        assertEquals("зп", operation.getDescription());
    }

    @Test
    @DisplayName("Тест создания операции c отображением рантайма")
    public void testCreateOperationWithRuntime() {
        Account account = accountFacade.createAccount("my_acc", 100L, false);
        Category category = categoryFacade.createCategory("Restaurant", CategoryType.CONSUMPTION, false);
        facade.createOperation(account, category, 100L, LocalDate.now(), "ресторан", true);

        assertEquals(0L, account.getBalance());
    }

    @Test
    @DisplayName("Тест удаления несуществующей операции")
    public void testDeleteNonExistingOperation() {
        assertThrows(RuntimeException.class, () -> facade.deleteOperation(0L, false));
    }

    @Test
    @DisplayName("Тест удаления операции")
    public void testDeleteOperation() {
        Account account = accountFacade.createAccount("my_acc", 0L, false);
        Category category = categoryFacade.createCategory("Wage", CategoryType.INCOME, false);
        Operation operation = facade.createOperation(account, category, 100L,
                LocalDate.now(), "зп", false);

        assertEquals(100L, account.getBalance());

        facade.deleteOperation(operation.getId(), false);

        Account updated_acc = accountFacade.getAccount(account.getId(), false);
        assertEquals(0L, updated_acc.getBalance());
        assertThrows(RuntimeException.class, () -> facade.deleteOperation(operation.getId(), false));

    }

    @Test
    @DisplayName("Тест удаления операции с рантаймом")
    public void testDeleteOperationWithRuntime() {
        Account account = accountFacade.createAccount("my_acc", 100L, false);
        Category category = categoryFacade.createCategory("Restaurant", CategoryType.CONSUMPTION, false);
        Operation operation = facade.createOperation(account, category, 100L,
                LocalDate.now(), "зп", false);

        facade.deleteOperation(operation.getId(), true);

    }

    @Test
    @DisplayName("Тест изменения несуществующей операции")
    public void testUpdateNonExistingOperation() {
        Account account = accountFacade.createAccount("my_acc", 0L, false);
        Category category = categoryFacade.createCategory("Wage", CategoryType.INCOME, false);
        assertThrows(RuntimeException.class, () -> facade.updateOperation(0L, account, category, 100L,
                LocalDate.now(), "зп", false));
    }

    @Test
    @DisplayName("Тест изменения операции")
    public void testUpdateOperation() {
        Account account = accountFacade.createAccount("my_acc", 0L, false);
        Category category = categoryFacade.createCategory("Wage", CategoryType.INCOME, false);
        Operation operation = facade.createOperation(account, category, 100L,
                LocalDate.now(), "зп", false);

        facade.updateOperation(operation.getId(), account, category, 50L,
                LocalDate.now(), "зп", false);
        Account updated_acc = accountFacade.getAccount(account.getId(), false);

        assertEquals(50L, updated_acc.getBalance());
    }


    @Test
    @DisplayName("Тест получения несуществующей операции")
    public void testGetNonExistingOperation() {
        assertThrows(RuntimeException.class, () -> facade.getOperation(0L, false));
    }

    @Test
    @DisplayName("Тест получения операции")
    public void testGetAccount() {
        Account account = accountFacade.createAccount("my_acc", 0L, false);
        Category category = categoryFacade.createCategory("Wage", CategoryType.INCOME, false);
        Operation operation = facade.createOperation(account, category, 100L,
                LocalDate.now(), "зп", false);

        Operation geted_operation = facade.getOperation(operation.getId(), false);

        assertEquals(operation.getAmount(), geted_operation.getAmount());
        assertEquals(operation.getDescription(), geted_operation.getDescription());
        assertEquals(operation.getDate(), geted_operation.getDate());
    }

}

