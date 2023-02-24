package me.leonidovich.anatoly.belov.webapp.service;

import me.leonidovich.anatoly.belov.webapp.model.Ingredient;

import java.util.Map;

public interface IngredientsService {
    void addIngredient(Ingredient ingredient);

    Ingredient getIngredientById(int id) ;

    void editIngredient(int id, Ingredient ingredient);

    Ingredient deleteIngredientById(int id);

    void deleteAllIngredients();

    Map<Integer, Ingredient> getIngredientMap();
}
