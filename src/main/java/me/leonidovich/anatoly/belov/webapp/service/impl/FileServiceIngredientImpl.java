package me.leonidovich.anatoly.belov.webapp.service.impl;

import me.leonidovich.anatoly.belov.webapp.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
@Service
public class FileServiceIngredientImpl implements FileService {
    @Value("${path.to.data.file}")
    private String dataFilePath;
    @Value("${name.of.ingredient.file}")
    private String ingredientsFileName;
    @Override
    public boolean saveToFile(String json) {
        try {
            Files.writeString(Path.of(dataFilePath, ingredientsFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
    @Override
    public String readFromFile(){
        try {
            return Files.readString(Path.of(dataFilePath, ingredientsFileName));
        } catch (IOException e) {
            try {
                Files.createFile(Path.of(dataFilePath, ingredientsFileName));
            } catch (IOException ex) {
                e.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
        return null;

    }

    @Override
    public File getDataFile() {
        return new File(dataFilePath + "/" + ingredientsFileName);
    }
    @Override
    public boolean cleanDataFile() {
        try {
            Path path = Path.of(dataFilePath, ingredientsFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
