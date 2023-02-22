package me.leonidovich.anatoly.belov.webapp.exeption;

public class RecipeIsNotCorrect extends RuntimeException {
    public RecipeIsNotCorrect(String message) {
        super(message);
    }
}
