package me.leonidovich.anatoly.belov.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    private String name;
    private int timeToCook;
    private List<Ingredient> ingredient;
    private final List<String> cookingSteps = new ArrayList<>();

}
