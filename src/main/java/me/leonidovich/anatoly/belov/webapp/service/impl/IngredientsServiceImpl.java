package me.leonidovich.anatoly.belov.webapp.service.impl;

import me.leonidovich.anatoly.belov.webapp.exeption.IdNotFound;
import me.leonidovich.anatoly.belov.webapp.exeption.IngredientIsNotCorrect;
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
        if (ingredient.getName() == null
                || ingredient.getUnitOfMeasurement() == null
                || ingredient.getName().isEmpty()
                || ingredient.getUnitOfMeasurement().isEmpty()) {
            throw new IngredientIsNotCorrect("Поля заполнены некорректно.Пожалуйста, введите корректные значения");
        }
        ingredientMap.put(count++, ingredient);
    }

    @Override
    public Ingredient getIngredientById(int id) {
        if (ingredientMap.isEmpty()) {
            throw new IdNotFound("Список ингредиентов пока пуст");
        }
        if (!ingredientMap.containsKey(id)) {
            throw new IdNotFound("Нет ингредиента под таким номером.Введите номер от 1 до " + ingredientMap.size());
        }


        return ingredientMap.get(id);
    }

    @Override
    public void editIngredient(int id, Ingredient ingredient) {
        ingredientMap.put(id, ingredient);
    }

    @Override
    public Ingredient deleteIngredientById(int id) {
        if (ingredientMap.isEmpty()) {
            throw new IdNotFound("Список ингредиентов пока пуст");
        }
        if (!ingredientMap.containsKey(id)) {
            throw new IdNotFound("Нет ингредиента под таким номером.Введите номер от 1 до " + ingredientMap.size());
        }
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
