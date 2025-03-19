package org.example.services;

import org.example.entities.Category;
import org.example.enums.CategoryType;
import org.example.interfaces.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category create(String name, CategoryType categoryType) {
        Category category = new Category();
        category.setName(name);
        category.setCategoryType(categoryType);
        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        this.get(id); // check that category with such id exist
        categoryRepository.deleteById(id);
    }

    public Category update(Long id, String newName, CategoryType categoryType) {
        Category category = this.get(id);
        category.setName(newName);
        category.setCategoryType(categoryType);
        return categoryRepository.save(category);
    }

    public Category get(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Категория не найдена"));
    }
}