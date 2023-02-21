package me.leonidovich.anatoly.belov.webapp.service;

import me.leonidovich.anatoly.belov.webapp.exeption.IdNotFound;
import me.leonidovich.anatoly.belov.webapp.exeption.RecipeIsNotCorrect;
import me.leonidovich.anatoly.belov.webapp.model.Recipe;

public interface RecipesService {
    void addRecipe(Recipe recipe);
    Recipe getRecipeById(int id) throws IdNotFound;
}
