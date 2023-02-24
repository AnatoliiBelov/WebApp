package me.leonidovich.anatoly.belov.webapp.service.impl;
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
        recipesMap.put(count++, recipe);
    }

    @Override
    public Recipe getRecipeById(int id) {
        return recipesMap.get(id);
    }

    @Override
    public void editRecipe(int id, Recipe recipe) {
        recipesMap.put(id, recipe);
    }

    @Override
    public Recipe deleteRecipeById(int id) {
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