package me.leonidovich.anatoly.belov.webapp.service;

import me.leonidovich.anatoly.belov.webapp.exeption.IdNotFound;
import me.leonidovich.anatoly.belov.webapp.exeption.IngredientIsNotCorrect;
import me.leonidovich.anatoly.belov.webapp.model.Ingredient;
import me.leonidovich.anatoly.belov.webapp.model.Recipe;

import java.util.Map;

public interface IngredientsService {
    void addIngredient(Ingredient ingredient);

    Ingredient getIngredientById(int id) throws IdNotFound;

    void editIngredient(int id, Ingredient ingredient);

    Ingredient deleteIngredientById(int id);

    void deleteAllIngredients();

    Map<Integer, Ingredient> getIngredientMap();
}
