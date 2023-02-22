package me.leonidovich.anatoly.belov.webapp.controllers;

import me.leonidovich.anatoly.belov.webapp.exeption.IdNotFound;
import me.leonidovich.anatoly.belov.webapp.exeption.RecipeIsNotCorrect;
import me.leonidovich.anatoly.belov.webapp.model.Ingredient;
import me.leonidovich.anatoly.belov.webapp.model.Recipe;
import me.leonidovich.anatoly.belov.webapp.service.RecipesService;
import me.leonidovich.anatoly.belov.webapp.service.impl.RecipesServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipesService recipesService;

    public RecipeController(RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @PostMapping
    public ResponseEntity<Recipe> addRecipe1(@RequestBody Recipe recipe) {
        if (recipe == null
                || recipe.getName() == null
                || recipe.getName().isEmpty()
                || recipe.getIngredient() == null
                || recipe.getCookingSteps().isEmpty()
                || recipe.getTimeToCook() == 0
        ) {
            return ResponseEntity.badRequest().body(recipe);
        }
        recipesService.addRecipe(recipe);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable int id) {
        if (recipesService.getRecipesMap().isEmpty()
                || !recipesService.getRecipesMap().containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(recipesService.getRecipeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable int id, @RequestBody Recipe recipe) {

        if (recipesService.getRecipesMap().isEmpty()
                || !recipesService.getRecipesMap().containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        if (recipe == null
                || recipe.getName() == null
                || recipe.getName().isEmpty()
                || recipe.getIngredient() == null
                || recipe.getCookingSteps().isEmpty()
                || recipe.getTimeToCook() == 0
        ) {
            return ResponseEntity.badRequest().body(recipe);
        }
        recipesService.editRecipe(id, recipe);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> deleteRecipeById(@PathVariable int id) {
        if (recipesService.getRecipesMap().isEmpty()
                || !recipesService.getRecipesMap().containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(recipesService.deleteRecipeById(id));
    }

    @DeleteMapping
    public ResponseEntity<Recipe> deleteAllRecipes() {
        if (recipesService.getRecipesMap().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        recipesService.deleteAllRecipe();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Map<Integer, Recipe>> getAllRecipe() {
        if (recipesService.getRecipesMap().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(recipesService.getRecipesMap());
    }

}
