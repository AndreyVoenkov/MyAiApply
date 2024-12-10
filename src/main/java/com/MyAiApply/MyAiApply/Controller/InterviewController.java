package com.MyAiApply.MyAiApply.Controller;

import com.MyAiApply.MyAiApply.Service.OpenAiServiceWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/interview")
public class InterviewController {

    @Autowired
    private OpenAiServiceWrapper openAiServiceWrapper;

    // Отображение страницы подготовки к собеседованию
    @GetMapping
    public String showInterviewPage() {
        return "interview"; // Возвращает файл interview.html
    }

    // Обработка запроса на получение ответа на технический вопрос
    @PostMapping("/ask")
    public String askTechnicalQuestion(@RequestParam String question, Model model) {
        String answer = openAiServiceWrapper.getTechnicalAnswer(question);
        model.addAttribute("question", question);
        model.addAttribute("answer", answer);
        return "interview-response";
    }
}