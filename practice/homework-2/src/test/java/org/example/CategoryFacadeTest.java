package org.example;

import org.example.entities.Category;
import org.example.enums.CategoryType;
import org.example.facades.CategoryFacade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CategoryFacadeTest {
    @Autowired
    private CategoryFacade facade;

    @Test
    @DisplayName("Тест создания категорий")
    public void testCreateCategory() {

        Category consumption_category = facade.createCategory("Restaurant", CategoryType.CONSUMPTION, false);

        assertEquals(CategoryType.CONSUMPTION, consumption_category.getCategoryType());
        assertEquals("Restaurant", consumption_category.getName());

        Category income_category = facade.createCategory("Wage", CategoryType.INCOME, false);

        assertEquals(CategoryType.INCOME, income_category.getCategoryType());
        assertEquals("Wage", income_category.getName());
    }

    @Test
    @DisplayName("Тест создания категорий c отображением рантайма")
    public void testCreateCategoryWithRuntime() {
        System.out.println("Создание категории за:");
        facade.createCategory("Restaurant", CategoryType.CONSUMPTION, true);
    }

    @Test
    @DisplayName("Тест удаления несуществующей категории")
    public void testDeleteNonExistingCategory() {
        assertThrows(RuntimeException.class, () -> facade.deleteCategory(0L, false));
    }

    @Test
    @DisplayName("Тест удаления категории")
    public void testDeleteCategory() {
        Category category = facade.createCategory("Restaurant", CategoryType.CONSUMPTION, false);
        facade.deleteCategory(category.getId(), false);

        assertThrows(RuntimeException.class, () -> facade.deleteCategory(category.getId(), false));
    }

    @Test
    @DisplayName("Тест удаления категории с рантаймом")
    public void testDeleteCategoryWithRuntime() {
        Category category = facade.createCategory("Restaurant", CategoryType.CONSUMPTION, false);
        System.out.println("Удаление категории за:");
        facade.deleteCategory(category.getId(), true);
    }

    @Test
    @DisplayName("Тест изменения несуществующей категории")
    public void testUpdateNonExistingCategory() {
        assertThrows(RuntimeException.class, () -> facade.updateCategory(0L, "Restaurant", CategoryType.CONSUMPTION, false));
    }

    @Test
    @DisplayName("Тест изменения категории")
    public void testUpdateCategory() {
        Category category = facade.createCategory("Restaurant", CategoryType.CONSUMPTION, false);
        Category edited = facade.updateCategory(category.getId(), "Shop", CategoryType.CONSUMPTION, false);

        assertEquals(category.getId(), edited.getId());
        assertEquals(CategoryType.CONSUMPTION, edited.getCategoryType());
        assertEquals("Shop", edited.getName());
    }

    @Test
    @DisplayName("Тест изменения категории с рантаймом")
    public void testUpdateCategoryWithRuntime() {
        Category category = facade.createCategory("Restaurant", CategoryType.CONSUMPTION, false);
        System.out.println("Изменение категории за:");
        facade.updateCategory(category.getId(), "Shop", CategoryType.CONSUMPTION, true);
    }

    @Test
    @DisplayName("Тест получения несуществующей категории")
    public void testGetNonExistingCategory() {
        assertThrows(RuntimeException.class, () -> facade.getCategory(0L, false));
    }

    @Test
    @DisplayName("Тест получения категории")
    public void testGetCategory() {
        Category category = facade.createCategory("Restaurant", CategoryType.CONSUMPTION, false);
        Category geted_category = facade.getCategory(category.getId(), false);

        assertEquals(category.getId(), geted_category.getId());
        assertEquals(category.getCategoryType(), geted_category.getCategoryType());
        assertEquals(category.getName(), geted_category.getName());
    }

    @Test
    @DisplayName("Тест получения категории с рантаймом")
    public void testGetCategoryWithRuntime() {
        Category category = facade.createCategory("Restaurant", CategoryType.CONSUMPTION, false);
        System.out.println("Получение категории за:");
        facade.getCategory(category.getId(), true);
    }

}

