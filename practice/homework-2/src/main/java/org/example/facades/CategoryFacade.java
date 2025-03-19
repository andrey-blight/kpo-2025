package org.example.facades;

import org.example.entities.Category;
import org.example.enums.CategoryType;
import org.example.factories.CommandCategoryFactory;
import org.example.interfaces.Command;
import org.example.interfaces.ResultCommand;
import org.springframework.stereotype.Component;

@Component
public class CategoryFacade {
    private final CommandCategoryFactory commandFactory;

    public CategoryFacade(CommandCategoryFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    public Category createCategory(String name, CategoryType categoryType, Boolean with_runtime) {
        ResultCommand<Category> command = this.commandFactory.createCategoryCommand(name, categoryType, with_runtime);
        command.execute();
        return command.getResult();
    }

    public void deleteCategory(Long id, Boolean with_runtime) {
        Command command = this.commandFactory.deleteCategoryCommand(id, with_runtime);
        command.execute();
    }

    public Category updateCategory(Long id, String newName, CategoryType categoryType, Boolean with_runtime) {
        ResultCommand<Category> command = this.commandFactory.updateCategoryCommand(id, newName, categoryType, with_runtime);
        command.execute();
        return command.getResult();
    }

    public Category getCategory(Long id, Boolean with_runtime) {
        ResultCommand<Category> command = this.commandFactory.getCategoryCommand(id, with_runtime);
        command.execute();
        return command.getResult();
    }
}
