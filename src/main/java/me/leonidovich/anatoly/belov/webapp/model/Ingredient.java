package me.leonidovich.anatoly.belov.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {
    private String name;
    private int countIngredients;
    private String UnitOfMeasurement;
}
