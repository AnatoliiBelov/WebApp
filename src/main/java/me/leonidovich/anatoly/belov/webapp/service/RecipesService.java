package me.leonidovich.anatoly.belov.webapp.service;

import me.leonidovich.anatoly.belov.webapp.exeption.IdNotFound;
import me.leonidovich.anatoly.belov.webapp.exeption.RecipeIsNotCorrect;
import me.leonidovich.anatoly.belov.webapp.model.Recipe;

import java.util.Map;

public interface RecipesService {
    void addRecipe(Recipe recipe);

    Recipe getRecipeById(int id) throws IdNotFound;

    void editRecipe(int id, Recipe recipe);

    Recipe deleteRecipeById(int id);

    void deleteAllRecipe();

    Map<Integer, Recipe> getRecipesMap();
}
