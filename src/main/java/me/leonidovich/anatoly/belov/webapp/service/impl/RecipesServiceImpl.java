package me.leonidovich.anatoly.belov.webapp.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.leonidovich.anatoly.belov.webapp.model.Recipe;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipesServiceImpl implements me.leonidovich.anatoly.belov.webapp.service.RecipesService {


    private HashMap<Integer, Recipe> recipesMap = new HashMap<>();
    private static int count;
    private final FileServiceRecipeImpl fileServiceRecipe;

    public RecipesServiceImpl(FileServiceRecipeImpl fileServiceRecipe) {
        this.fileServiceRecipe = fileServiceRecipe;
    }

    @Override
    public int addRecipe(Recipe recipe) {
        recipesMap.put(count, recipe);
        count++;
        saveToFile();
        return count-1;
    }

    @Override
    public Recipe getRecipeById(int id) {
        return recipesMap.get(id);
    }

    @Override
    public void editRecipe(int id, Recipe recipe) {
        recipesMap.put(id, recipe);
        saveToFile();
    }

    @Override
    public Recipe deleteRecipeById(int id) {
        Recipe recipe;
        recipe = getRecipeById(id);
        recipesMap.remove(id);
        saveToFile();
        return recipe;

    }

    @Override
    public void deleteAllRecipe() {
        recipesMap.clear();
        saveToFile();
    }

    @Override
    public Map<Integer, Recipe> getRecipesMap() {
        return recipesMap;
    }

    private void saveToFile() {
        try {
            String valueAsString = new ObjectMapper().writeValueAsString(recipesMap);
            fileServiceRecipe.saveToFile(valueAsString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    ;


    private void readFromFile() {
        String json = fileServiceRecipe.readFromFile();
        try {
            recipesMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            recipesMap=new HashMap<>();
        }
    }

    @PostConstruct
    private void init() {
        readFromFile();
        count = recipesMap.size();
    }
}