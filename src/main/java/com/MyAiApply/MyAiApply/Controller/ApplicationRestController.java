package com.MyAiApply.MyAiApply.Controller;

import com.MyAiApply.MyAiApply.model.Application;
import com.MyAiApply.MyAiApply.repository.ApplicationRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
@Tag(name = "Applications API", description = "API для управления заявками")
public class ApplicationRestController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @PostMapping("/add")
    @Operation(summary = "Добавить новую заявку")
    public ResponseEntity<String> addApplication(@RequestBody Application application) {
        applicationRepository.save(application);
        return ResponseEntity.ok("Заявка добавлена");
    }
}