package me.leonidovich.anatoly.belov.webapp.controllers;

import me.leonidovich.anatoly.belov.webapp.exeption.IdNotFound;
import me.leonidovich.anatoly.belov.webapp.exeption.IngredientIsNotCorrect;
import me.leonidovich.anatoly.belov.webapp.model.Ingredient;

import me.leonidovich.anatoly.belov.webapp.service.IngredientsService;
import me.leonidovich.anatoly.belov.webapp.service.impl.IngredientsServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private IngredientsService ingredientsService;

    public IngredientController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @PostMapping
    public void addIngredient(@RequestBody Ingredient ingredient)  {
       ingredientsService.addIngredient(ingredient);
    }
@GetMapping
    public Ingredient getIngredient(@PathVariable int id) {
       return ingredientsService.getIngredientById(id);
    }
}
