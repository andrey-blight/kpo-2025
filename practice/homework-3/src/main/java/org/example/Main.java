package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);

//        CategoryFacade categoryFacade = context.getBean(CategoryFacade.class);
//
//        Category food_cat = categoryFacade.createCategory("Food", CategoryType.CONSUMPTION, false);
//
//        System.out.println(food_cat.getId());
    }
}