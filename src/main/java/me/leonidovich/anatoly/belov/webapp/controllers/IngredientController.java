package me.leonidovich.anatoly.belov.webapp.controllers;

import me.leonidovich.anatoly.belov.webapp.exeption.IdNotFound;
import me.leonidovich.anatoly.belov.webapp.exeption.IngredientIsNotCorrect;
import me.leonidovich.anatoly.belov.webapp.model.Ingredient;

import me.leonidovich.anatoly.belov.webapp.service.impl.IngredientsServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private IngredientsServiceImpl ingredientsService;

    @PostMapping("/add")
    public void addIngredient(@RequestBody Ingredient ingredient) throws IngredientIsNotCorrect {
       ingredientsService.addIngredient(ingredient);
    }
@GetMapping("/get")
    public Ingredient getIngredient(@RequestParam int id) throws IdNotFound {
       return ingredientsService.getIngredientById(id);
    }
}
