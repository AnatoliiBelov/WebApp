package me.leonidovich.anatoly.belov.webapp.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.leonidovich.anatoly.belov.webapp.model.Ingredient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientsServiceImpl implements me.leonidovich.anatoly.belov.webapp.service.IngredientsService {
    private HashMap<Integer, Ingredient> ingredientMap = new HashMap<>();
    private static int count;
    private final FileServiceIngredientImpl fileServiceIngredient;

    public IngredientsServiceImpl(FileServiceIngredientImpl fileServiceIngredient) {
        this.fileServiceIngredient = fileServiceIngredient;
    }

    @Override
    public int addIngredient(Ingredient ingredient) {
        ingredientMap.put(count, ingredient);
        count++;
        saveToFile();
        return count-1;
    }

    @Override
    public Ingredient getIngredientById(int id) {
        return ingredientMap.get(id);
    }

    @Override
    public void editIngredient(int id, Ingredient ingredient) {
        ingredientMap.put(id, ingredient);
        saveToFile();
    }

    @Override
    public Ingredient deleteIngredientById(int id) {
        Ingredient ingredient;
        ingredient = getIngredientById(id);
        ingredientMap.remove(id);
        saveToFile();
        return ingredient;
    }

    @Override
    public void deleteAllIngredients() {
        ingredientMap.clear();
        saveToFile();
    }

    @Override
    public HashMap<Integer, Ingredient> getIngredientMap() {
        return ingredientMap;
    }

    private void saveToFile() {
        try {
            String valueAsString = new ObjectMapper().writeValueAsString(ingredientMap);
            fileServiceIngredient.saveToFile(valueAsString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    ;


    private void readFromFile() {
        String json = fileServiceIngredient.readFromFile();
        try {
            ingredientMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            ingredientMap=new HashMap<>();
        }
    }

    @PostConstruct
    private void init() {
        readFromFile();
        count = ingredientMap.size();
    }
}
