package me.leonidovich.anatoly.belov.webapp.service.impl;

import me.leonidovich.anatoly.belov.webapp.exeption.IdNotFound;
import me.leonidovich.anatoly.belov.webapp.exeption.IngredientIsNotCorrect;
import me.leonidovich.anatoly.belov.webapp.model.Ingredient;
import me.leonidovich.anatoly.belov.webapp.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientsServiceImpl implements me.leonidovich.anatoly.belov.webapp.service.IngredientsService {
    private final Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    private static Integer count = 0;

    @Override
    public void addIngredient(Ingredient ingredient) throws IngredientIsNotCorrect {
        if (ingredient.getName()==null
                ||ingredient.getUnitOfMeasurement()==null
                ||ingredient.getName().isEmpty()
                ||ingredient.getUnitOfMeasurement().isEmpty())
        {
            throw new IngredientIsNotCorrect("Поля заполнены некорректно.Пожалуйста, введите корректные значения");
        }
        ingredientMap.put(count++, ingredient);
    }

    @Override
    public Ingredient getIngredientById(int id) throws IdNotFound {
        if (ingredientMap.isEmpty()) {
            throw new IdNotFound("Список ингредиентов пока пуст");
        }
        if (ingredientMap.get(id) == null) {
            throw new IdNotFound("Нет ингредиента под таким номером.Введите номер от 1 до " + ingredientMap.size());
        }


        return ingredientMap.get(id);
    }
}
