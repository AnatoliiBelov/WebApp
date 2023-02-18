package me.leonidovich.anatoly.belov.webapp.controllers;

import me.leonidovich.anatoly.belov.webapp.exeption.IdNotFound;
import me.leonidovich.anatoly.belov.webapp.exeption.RecipeIsNotCorrect;
import me.leonidovich.anatoly.belov.webapp.model.Recipe;
import me.leonidovich.anatoly.belov.webapp.service.impl.RecipesServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController("/recipe")
public class RecipeController {
    private RecipesServiceImpl recipesService;



    @PostMapping("/add")
    public void addRecipe1(@RequestBody Recipe recipe) throws RecipeIsNotCorrect {
        recipesService.addRecipe(recipe);

    }
    @GetMapping("/get")
    public Recipe getRecipe(@RequestParam int id) throws IdNotFound {
       return recipesService.getRecipeById(id);
    }
}
