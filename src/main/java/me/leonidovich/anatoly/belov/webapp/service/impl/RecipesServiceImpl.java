package me.leonidovich.anatoly.belov.webapp.service.impl;

import me.leonidovich.anatoly.belov.webapp.exeption.IdNotFound;
import me.leonidovich.anatoly.belov.webapp.exeption.IngredientIsNotCorrect;
import me.leonidovich.anatoly.belov.webapp.exeption.RecipeIsNotCorrect;
import me.leonidovich.anatoly.belov.webapp.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class RecipesServiceImpl implements me.leonidovich.anatoly.belov.webapp.service.RecipesService {
    private final Map<Integer, Recipe> recipesMap = new HashMap<>();
    private static Integer count = 0;

    @Override
    public void addRecipe(Recipe recipe) throws RecipeIsNotCorrect {
        if (recipe==null
                ||recipe.getName() == null
                || recipe.getName().isEmpty()
                || recipe.getIngredient() == null
                || recipe.getCookingSteps().isEmpty()
                || recipe.getCookingSteps() == null
                || recipe.getTimeToCook() == 0
                ) {
            throw new RecipeIsNotCorrect("Поля заполнены некорректно.Пожалуйста, введите корректные значения");
        }
        recipesMap.put(count++, recipe);
    }

    @Override
    public Recipe getRecipeById(int id) throws IdNotFound {
        if (recipesMap.isEmpty()) {
            throw new IdNotFound("Список рецептов пока пуст");
        }
        if (recipesMap.get(id) == null) {
            throw new IdNotFound("Нет рецепта под таким номером.Введите номер от 1 до " + recipesMap.size());
        }
        return recipesMap.get(id);
    }
}