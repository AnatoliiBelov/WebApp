package me.leonidovich.anatoly.belov.webapp.service;

import me.leonidovich.anatoly.belov.webapp.exeption.IdNotFound;
import me.leonidovich.anatoly.belov.webapp.exeption.IngredientIsNotCorrect;
import me.leonidovich.anatoly.belov.webapp.model.Ingredient;
import me.leonidovich.anatoly.belov.webapp.model.Recipe;

public interface IngredientsService {
    void addIngredient(Ingredient ingredient) throws IngredientIsNotCorrect;
    Ingredient getIngredientById(int id) throws IdNotFound;
}
