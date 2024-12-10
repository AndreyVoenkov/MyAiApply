package com.MyAiApply.MyAiApply.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Отображение главной страницы
    @GetMapping("/")
    public String home() {
        return "index"; // Возвращает файл index.html
    }
}