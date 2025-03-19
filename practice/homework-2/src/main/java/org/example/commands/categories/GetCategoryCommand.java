package org.example.commands.categories;

import lombok.Getter;
import org.example.entities.Category;
import org.example.interfaces.ResultCommand;
import org.example.services.CategoryService;

public class GetCategoryCommand implements ResultCommand<Category> {
    private final CategoryService categoryService;
    private final Long id;

    @Getter
    private Category result;

    public GetCategoryCommand(CategoryService categoryService, Long id) {
        this.categoryService = categoryService;
        this.id = id;
    }

    @Override
    public void execute() {
        result = categoryService.get(id);
    }
}
