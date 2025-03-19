package org.example.commands.categories;

import lombok.Getter;
import org.example.entities.Category;
import org.example.enums.CategoryType;
import org.example.interfaces.ResultCommand;
import org.example.services.CategoryService;

public class CreateCategoryCommand implements ResultCommand<Category> {
    private final CategoryService categoryService;
    private final String categoryName;
    private final CategoryType categoryType;
    @Getter
    private Category result;

    public CreateCategoryCommand(CategoryService categoryService, String categoryName, CategoryType categoryType) {
        this.categoryService = categoryService;
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    @Override
    public void execute() {
        this.result = categoryService.create(categoryName, categoryType);
    }
}