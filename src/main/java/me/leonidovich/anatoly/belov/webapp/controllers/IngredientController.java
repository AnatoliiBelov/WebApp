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
import me.leonidovich.anatoly.belov.webapp.service.IngredientsService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingredient")
@Tag(name="Ингредиенты", description = "CRUD-операции и другие эндпоинты")
public class IngredientController {
    private final IngredientsService ingredientsService;

    public IngredientController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @PostMapping
    @Operation(
            summary = "добавление ингредиента  в книгу ингредиентов",
            description = "Ингредиенты нужно обязательно добавлять согласно схеме"
    )
    @ApiResponses(value = {@ApiResponse(
            responseCode = "400",
            description = "Проверьте, пожалуйста, Ваш ингредиент согласно схеме. Скорректируйте ингредиента и повторите попытку"
    ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Отлично! Ингредиент добавлен"
            )
    }
    )
    public ResponseEntity<?> addIngredient(@RequestBody Ingredient ingredient) {
        if (ObjectUtils.isEmpty(ingredient))  {
            return ResponseEntity.badRequest().body(ingredient);
        }
        int id = ingredientsService.addIngredient(ingredient);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение ингредиента из книги ингредиентов",
            description = "Поиск происходит по идентификационному номеру.Нужно указать id для получения ингредиента"
    )
    @ApiResponses(value = {@ApiResponse(
            responseCode = "404",
            description = "Нет ингредиента под таким номером"
    ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Отлично! Ингредиент получен",
                    content={
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Ingredient.class)

                                    )
                    }
            )
    }
    )
    public ResponseEntity<Ingredient> getIngredient(@PathVariable int id) {
        if (ObjectUtils.isEmpty(ingredientsService.getIngredientMap())
                || !ingredientsService.getIngredientMap().containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(ingredientsService.getIngredientById(id));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Редактирование ингредиента в книге ингредиентов",
            description = "Поиск происходит по идентификационному номеру.Нужно указать id для редактирования ингредиента"
    )
    @ApiResponses(value = {@ApiResponse(
            responseCode = "404",
            description = "Нет ингредиента под таким номером"
    ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Проверьте, пожалуйста, Ваш ингредиент согласно схеме. Скорректируйте ингредиент и повторите попытку"
            ),
            @ApiResponse(
                    responseCode = "200",
                    description = "Отлично! Рецепт изменен"

            )
    }
    )
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int id, @RequestBody Ingredient ingredient) {
        if (ObjectUtils.isEmpty(ingredientsService.getIngredientMap())
                || !ingredientsService.getIngredientMap().containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        if (ObjectUtils.isEmpty(ingredient)) {
            return ResponseEntity.badRequest().body(ingredient);
        }
        ingredientsService.editIngredient(id, ingredient);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление ингредиента в книге ингредиентов",
            description = "Поиск происходит по идентификационному номеру.Нужно указать id для удаления ингредиента"
    )
    @ApiResponses(value = {@ApiResponse(
            responseCode = "404",
            description = "Нет ингредиента под таким номером"
    ),

            @ApiResponse(
                    responseCode = "200",
                    description = "Отлично! Ингредиент удален",
                    content={
                            @Content(mediaType = "json",
                                    schema = @Schema(implementation = Ingredient.class)

                            )
                    }

            )
    }
    )
    public ResponseEntity<Ingredient> deleteIngredientById(@PathVariable int id) {
        if (ObjectUtils.isEmpty(ingredientsService.getIngredientMap())
                || !ingredientsService.getIngredientMap().containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        Ingredient ingredient;
        ingredient = ingredientsService.deleteIngredientById(id);
        return ResponseEntity.ok().body(ingredient);
    }

    @DeleteMapping
    @Operation(
            summary = "Удаление всех ингредиентов в книге ингредиентов"
    )
    @ApiResponses(value = {@ApiResponse(
            responseCode = "404",
            description = "Книга ингредиентов уже пуста"
    ),

            @ApiResponse(
                    responseCode = "200",
                    description = "Отлично! Все ингредиенты удалены"
            )
    }
    )
    public ResponseEntity<Ingredient> deleteAllIngredients() {
        if (ObjectUtils.isEmpty(ingredientsService.getIngredientMap())) {
            return ResponseEntity.notFound().build();
        }
        ingredientsService.deleteAllIngredients();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(
            summary = "Получение всех ингредиентов из книги"
    )
    @ApiResponses(value = {@ApiResponse(
            responseCode = "404",
            description = "Книга ингредиентов пуста"
    ),

            @ApiResponse(
                    responseCode = "200",
                    description = "Отлично!Список ингредиентов получен",
                    content={
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = Ingredient.class)

                                    ))
                    }

            )
    }
    )
    public ResponseEntity<Map<Integer, Ingredient>> getAllIngredient() {
        if (ObjectUtils.isEmpty(ingredientsService.getIngredientMap())
        ) {
            return ResponseEntity.ok(ingredientsService.getIngredientMap());
        }
        return ResponseEntity.ok().body(ingredientsService.getIngredientMap());
    }

}
