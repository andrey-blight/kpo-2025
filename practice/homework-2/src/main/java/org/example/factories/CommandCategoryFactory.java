package org.example.factories;

import org.example.commands.categories.CreateCategoryCommand;
import org.example.commands.categories.DeleteCategoryCommand;
import org.example.commands.categories.GetCategoryCommand;
import org.example.commands.categories.UpdateCategoryCommand;
import org.example.decorators.RuntimeDecorator;
import org.example.decorators.RuntimeResultDecorator;
import org.example.entities.Category;
import org.example.enums.CategoryType;
import org.example.interfaces.Command;
import org.example.interfaces.ResultCommand;
import org.example.services.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class CommandCategoryFactory {
    private final CategoryService categoryService;

    public CommandCategoryFactory(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public ResultCommand<Category> createCategoryCommand(String categoryName, CategoryType categoryType, Boolean with_runtime) {
        ResultCommand<Category> command = new CreateCategoryCommand(categoryService, categoryName, categoryType);

        if (with_runtime) {
            return new RuntimeResultDecorator<>(command);
        }
        return command;
    }

    public Command deleteCategoryCommand(Long id, Boolean with_runtime) {
        Command command = new DeleteCategoryCommand(categoryService, id);

        if (with_runtime) {
            return new RuntimeDecorator(command);
        }
        return command;
    }

    public ResultCommand<Category> updateCategoryCommand(Long id, String name, CategoryType categoryType, Boolean with_runtime) {
        ResultCommand<Category> command = new UpdateCategoryCommand(categoryService, id, name, categoryType);

        if (with_runtime) {
            return new RuntimeResultDecorator<>(command);
        }
        return command;
    }

    public ResultCommand<Category> getCategoryCommand(Long id, Boolean with_runtime) {
        ResultCommand<Category> command = new GetCategoryCommand(categoryService, id);

        if (with_runtime) {
            return new RuntimeResultDecorator<>(command);
        }
        return command;
    }
}
