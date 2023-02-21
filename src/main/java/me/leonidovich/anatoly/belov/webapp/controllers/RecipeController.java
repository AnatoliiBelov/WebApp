package me.leonidovich.anatoly.belov.webapp.controllers;

import me.leonidovich.anatoly.belov.webapp.exeption.IdNotFound;
import me.leonidovich.anatoly.belov.webapp.exeption.RecipeIsNotCorrect;
import me.leonidovich.anatoly.belov.webapp.model.Recipe;
import me.leonidovich.anatoly.belov.webapp.service.RecipesService;
import me.leonidovich.anatoly.belov.webapp.service.impl.RecipesServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private RecipesService recipesService;

    public RecipeController(RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @PostMapping
    public void addRecipe1(@RequestBody Recipe recipe) {
        recipesService.addRecipe(recipe);

    }
    @GetMapping
    public Recipe getRecipe(@PathVariable int id) {
       return recipesService.getRecipeById(id);
    }
}
