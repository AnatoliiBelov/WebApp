package me.leonidovich.anatoly.belov.webapp.service;

public interface FileService {
    boolean saveToFile(String json);

    String readFromFile();

}
