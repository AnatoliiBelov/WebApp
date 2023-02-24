package me.leonidovich.anatoly.belov.webapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import me.leonidovich.anatoly.belov.webapp.model.Ingredient;
import me.leonidovich.anatoly.belov.webapp.model.Recipe;
import me.leonidovich.anatoly.belov.webapp.service.RecipesService;
import me.leonidovich.anatoly.belov.webapp.service.impl.RecipesServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "CRUD-операции и другие эндпоинты")
public class RecipeController {
    private final RecipesService recipesService;

    public RecipeController(RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @PostMapping
    @Operation(
            summary = "добавление рецепта в книгу рецептов",
            description = "Рецепты нужно обязательно добавлять согласно схеме"
    )
    @ApiResponses(value = {@ApiResponse(
            responseCode = "400",
            description = "Проверьте, пожалуйста, Ваш рецепт согласно схеме. Скорректируйте рецепт и повторите попытку"
    ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Отлично! Рецепт добавлен"
            )
    }
    )
    public ResponseEntity<Recipe> addRecipe1(@RequestBody Recipe recipe) {
        if (ObjectUtils.isEmpty(recipe)
        ) {
            return ResponseEntity.badRequest().body(recipe);
        }
        recipesService.addRecipe(recipe);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение рецепта из книги рецептов",
            description = "Поиск происходит по идентификационному номеру.Нужно указать id для получения рецепта"
    )
    @ApiResponses(value = {@ApiResponse(
            responseCode = "404",
            description = "Нет рецепта под таким номером"
    ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Отлично! Рецепт получен",
                    content={
                            @Content(mediaType = "json",
                                    schema = @Schema(implementation = Recipe.class)

                            )
                    }
            )
    }
    )
    public ResponseEntity<Recipe> getRecipe(@PathVariable int id) {
        if (ObjectUtils.isEmpty(recipesService.getRecipesMap())
                || !recipesService.getRecipesMap().containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(recipesService.getRecipeById(id));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Редактирование рецепта в книге рецептов",
            description = "Поиск происходит по идентификационному номеру.Нужно указать id для редактирования рецепта"
    )
    @ApiResponses(value = {@ApiResponse(
            responseCode = "404",
            description = "Нет рецепта под таким номером"
    ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Проверьте, пожалуйста, Ваш рецепт согласно схеме. Скорректируйте рецепт и повторите попытку"
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Отлично! Рецепт изменен"

            )
    }
    )
    public ResponseEntity<Recipe> editRecipe(@PathVariable int id, @RequestBody Recipe recipe) {

        if (ObjectUtils.isEmpty(recipesService.getRecipesMap())
                || !recipesService.getRecipesMap().containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        if (ObjectUtils.isEmpty(recipe)
        ) {
            return ResponseEntity.badRequest().body(recipe);
        }
        recipesService.editRecipe(id, recipe);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление рецепта в книге рецептов",
            description = "Поиск происходит по идентификационному номеру.Нужно указать id для удаления рецепта"
    )
    @ApiResponses(value = {@ApiResponse(
            responseCode = "404",
            description = "Нет рецепта под таким номером"
    ),

            @ApiResponse(
                    responseCode = "200",
                    description = "Отлично! Рецепт удален",
                    content={
                            @Content(mediaType = "json",
                                    schema = @Schema(implementation = Recipe.class)

                            )
                    }

            )
    }
    )
    public ResponseEntity<Recipe> deleteRecipeById(@PathVariable int id) {
        if (ObjectUtils.isEmpty(recipesService.getRecipesMap())
                || !recipesService.getRecipesMap().containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(recipesService.deleteRecipeById(id));
    }

    @DeleteMapping
    @Operation(
            summary = "Удаление всех рецептов в книге рецептов"
    )
    @ApiResponses(value = {@ApiResponse(
            responseCode = "404",
            description = "Книга рецептов уже пуста"
    ),

            @ApiResponse(
                    responseCode = "200",
                    description = "Отлично! Все рецепты удалены"
            )
    }
    )
    public ResponseEntity<Recipe> deleteAllRecipes() {
        if (ObjectUtils.isEmpty(recipesService.getRecipesMap())) {
            return ResponseEntity.notFound().build();
        }
        recipesService.deleteAllRecipe();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(
            summary = "Получение всех рецептов из книги"
    )
    @ApiResponses(value = {@ApiResponse(
            responseCode = "404",
            description = "Книга рецептов пуста"
    ),

            @ApiResponse(
                    responseCode = "200",
                    description = "Отлично!Список рецептов получен",
                    content={
                            @Content(mediaType = "json",
                                    array = @ArraySchema(
                                    schema = @Schema(implementation = Recipe.class)

                            ))
                    }

            )
    }
    )
    public ResponseEntity<Map<Integer, Recipe>> getAllRecipe() {
        if (ObjectUtils.isEmpty(recipesService.getRecipesMap())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(recipesService.getRecipesMap());
    }

}
