package me.leonidovich.anatoly.belov.webapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
@RestController
public class FirstController {
    @GetMapping("/")
    public String appRun(){
        return "Приложение запущено";
    }

    @GetMapping("/info")
    public String info() {
        return "Автор:Белов Анатолий Леонидович<br>" +
                "Приложение WebApp<br>" +
                "Создано 16.02.2023<br>" +
                "Описание: Приложение, которое получится в процессе обучения:)";
    }
}
