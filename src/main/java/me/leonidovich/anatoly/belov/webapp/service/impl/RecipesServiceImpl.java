package me.leonidovich.anatoly.belov.webapp.service.impl;

import me.leonidovich.anatoly.belov.webapp.exeption.IdNotFound;
import me.leonidovich.anatoly.belov.webapp.exeption.RecipeIsNotCorrect;
import me.leonidovich.anatoly.belov.webapp.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipesServiceImpl implements me.leonidovich.anatoly.belov.webapp.service.RecipesService {


    private final Map<Integer, Recipe> recipesMap = new HashMap<>();
    private static Integer count = 0;

    @Override
    public void addRecipe(Recipe recipe) {
        if (recipe == null
                || recipe.getName() == null
                || recipe.getName().isEmpty()
                || recipe.getIngredient() == null
                || recipe.getCookingSteps().isEmpty()
                || recipe.getTimeToCook() == 0
        ) {
            throw new RecipeIsNotCorrect("Поля заполнены некорректно.Пожалуйста, введите корректные значения");
        }
        recipesMap.put(count++, recipe);
    }

    @Override
    public Recipe getRecipeById(int id) {
        if (recipesMap.isEmpty()) {
            throw new IdNotFound("Список рецептов пока пуст");
        }
        if (!recipesMap.containsKey(id)) {
            throw new IdNotFound("Нет рецепта под таким номером.Введите номер от 0 до " + recipesMap.size());
        }
        return recipesMap.get(id);
    }

    @Override
    public void editRecipe(int id, Recipe recipe) {

        if (!recipesMap.containsKey(id)) {
            throw new IdNotFound("Нет рецепта под таким номером.Введите номер от 0 до " + recipesMap.size());
        }
        if (recipe == null
                || recipe.getName() == null
                || recipe.getName().isEmpty()
                || recipe.getIngredient() == null
                || recipe.getCookingSteps().isEmpty()
                || recipe.getTimeToCook() == 0
        ) {
            throw new RecipeIsNotCorrect("Поля заполнены некорректно.Пожалуйста, введите корректные значения");
        }
        recipesMap.put(id, recipe);
    }

    @Override
    public Recipe deleteRecipeById(int id) {
        if (recipesMap.isEmpty()) {
            throw new IdNotFound("Список рецептов пока пуст");
        }
        if (!recipesMap.containsKey(id)) {
            throw new IdNotFound("Нет рецепта под таким номером.Введите номер от 0 до " + recipesMap.size());
        }

        Recipe recipe;
        recipe = getRecipeById(id);
        recipesMap.remove(id);
        return recipe;
    }

    @Override
    public void deleteAllRecipe() {
        recipesMap.clear();
    }

    @Override
    public Map<Integer, Recipe> getRecipesMap() {
        return recipesMap;
    }

}