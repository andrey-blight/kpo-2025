package org.example.commands.categories;

import lombok.Getter;
import org.example.entities.Category;
import org.example.enums.CategoryType;
import org.example.interfaces.ResultCommand;
import org.example.services.CategoryService;

public class UpdateCategoryCommand implements ResultCommand<Category> {
    private final CategoryService categoryService;
    private final Long id;
    private final String name;
    private final CategoryType categoryType;

    @Getter
    private Category result;

    public UpdateCategoryCommand(CategoryService categoryService, Long id, String name, CategoryType categoryType) {
        this.categoryService = categoryService;
        this.id = id;
        this.name = name;
        this.categoryType = categoryType;
    }

    @Override
    public void execute() {
        result = categoryService.update(id, name, categoryType);
    }
}
