package me.leonidovich.anatoly.belov.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
public class Recipe {
    private String name;
    private int timeToCook;
    private List<Ingredient> ingredient;
    private final List<String> cookingSteps = new ArrayList<>();

}
