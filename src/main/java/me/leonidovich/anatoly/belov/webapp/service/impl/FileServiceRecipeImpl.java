package me.leonidovich.anatoly.belov.webapp.service.impl;

import me.leonidovich.anatoly.belov.webapp.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceRecipeImpl implements FileService {

    @Value("${path.to.data.file}")
    private String dataFilePath;
    @Value("${name.of.recipe.file}")
    private String recipesFileName;
@Override
    public boolean saveToFile(String json) {
        try {
            Files.writeString(Path.of(dataFilePath, recipesFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }

    }
    @Override
public String readFromFile(){
    try {
       return Files.readString(Path.of(dataFilePath, recipesFileName));
    } catch (IOException e) {
        try {
            Files.createFile(Path.of(dataFilePath, recipesFileName));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }


}


}
