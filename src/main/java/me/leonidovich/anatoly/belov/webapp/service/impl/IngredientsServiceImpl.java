package me.leonidovich.anatoly.belov.webapp.service.impl;
import me.leonidovich.anatoly.belov.webapp.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientsServiceImpl implements me.leonidovich.anatoly.belov.webapp.service.IngredientsService {
    private final Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    private static Integer count = 0;

    @Override
    public void addIngredient(Ingredient ingredient) {
        ingredientMap.put(count++, ingredient);
    }

    @Override
    public Ingredient getIngredientById(int id) {
        return ingredientMap.get(id);
    }

    @Override
    public void editIngredient(int id, Ingredient ingredient) {
        ingredientMap.put(id, ingredient);
    }

    @Override
    public Ingredient deleteIngredientById(int id) {
        Ingredient ingredient;
        ingredient = getIngredientById(id);
        ingredientMap.remove(id);
        return ingredient;
    }

    @Override
    public void deleteAllIngredients() {
        ingredientMap.clear();
    }

    @Override
    public Map<Integer, Ingredient> getIngredientMap() {
        return ingredientMap;
    }
}
