package org.example.commands.categories;

import org.example.interfaces.Command;
import org.example.services.CategoryService;

public class DeleteCategoryCommand implements Command {
    private final CategoryService categoryService;
    private final Long id;

    public DeleteCategoryCommand(CategoryService categoryService, Long id) {
        this.categoryService = categoryService;
        this.id = id;
    }

    @Override
    public void execute() {
        categoryService.delete(id);
    }
}
