package me.leonidovich.anatoly.belov.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    private String name;
    private int countIngredients;
    private String unitOfMeasurement;
}
