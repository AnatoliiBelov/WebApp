package me.leonidovich.anatoly.belov.webapp.controllers;

import me.leonidovich.anatoly.belov.webapp.service.impl.FileServiceIngredientImpl;
import me.leonidovich.anatoly.belov.webapp.service.impl.FileServiceRecipeImpl;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files")
public class FilesController {
    private final FileServiceRecipeImpl filesServiceReciple;
    private final FileServiceIngredientImpl filesServiceIngredient;

    public FilesController(FileServiceRecipeImpl filesServiceReciple, FileServiceIngredientImpl filesServiceIngredient) {
        this.filesServiceReciple = filesServiceReciple;
        this.filesServiceIngredient = filesServiceIngredient;
    }


    @GetMapping("/exportRecipe")
    public ResponseEntity<InputStreamResource> dowloadRecipeDataFile() throws FileNotFoundException {
        File file = filesServiceReciple.getDataFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipeData.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();

        }
    }

    @GetMapping("/exportIngredient")
    public ResponseEntity<InputStreamResource> dowloadIngredientDataFile() throws FileNotFoundException {
        File file = filesServiceIngredient.getDataFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"IngredientData.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();

        }
    }

    @PostMapping("/importRecipe")
    public ResponseEntity<Void> uploadRecipeDataFile(@RequestParam MultipartFile file) {
        filesServiceReciple.cleanDataFile();
        File dataFile = filesServiceReciple.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @PostMapping("/importIngredient")
    public ResponseEntity<Void> uploadIngredientDataFile(@RequestParam MultipartFile file) {
        filesServiceIngredient.cleanDataFile();
        File dataFile = filesServiceIngredient.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
