package me.leonidovich.anatoly.belov.webapp.exeption;

public class IngredientIsNotCorrect extends RuntimeException {
    public IngredientIsNotCorrect(String message) {
        super(message);
    }
}
