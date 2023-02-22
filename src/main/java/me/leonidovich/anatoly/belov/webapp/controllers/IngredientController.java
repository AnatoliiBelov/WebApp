package me.leonidovich.anatoly.belov.webapp.controllers;

import me.leonidovich.anatoly.belov.webapp.model.Ingredient;

import me.leonidovich.anatoly.belov.webapp.service.IngredientsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientsService ingredientsService;

    public IngredientController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @PostMapping
    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient) {
        if (ingredient.getName() == null
                || ingredient.getUnitOfMeasurement() == null
                || ingredient.getName().isEmpty()
                || ingredient.getUnitOfMeasurement().isEmpty()) {
            return ResponseEntity.badRequest().body(ingredient);
        }
        ingredientsService.addIngredient(ingredient);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable int id) {
        if (ingredientsService.getIngredientMap().isEmpty()
                || !ingredientsService.getIngredientMap().containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(ingredientsService.getIngredientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int id, @RequestBody Ingredient ingredient) {
        if (ingredientsService.getIngredientMap().isEmpty()
                || !ingredientsService.getIngredientMap().containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        if (ingredient.getName() == null
                || ingredient.getUnitOfMeasurement() == null
                || ingredient.getName().isEmpty()
                || ingredient.getUnitOfMeasurement().isEmpty()) {
            return ResponseEntity.badRequest().body(ingredient);
        }
        ingredientsService.editIngredient(id, ingredient);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ingredient> deleteIngredientById(@PathVariable int id) {
        if (ingredientsService.getIngredientMap().isEmpty()
                || !ingredientsService.getIngredientMap().containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        Ingredient ingredient;
        ingredient = ingredientsService.deleteIngredientById(id);
        return ResponseEntity.ok().body(ingredient);
    }

    @DeleteMapping
    public ResponseEntity<Ingredient> deleteAllIngredients() {
        if (ingredientsService.getIngredientMap().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ingredientsService.deleteAllIngredients();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Map<Integer, Ingredient>> getAllIngredient() {
        if (ingredientsService.getIngredientMap().isEmpty()
        ) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(ingredientsService.getIngredientMap());
    }

}
