package com.MyAiApply.MyAiApply.Controller;

import com.MyAiApply.MyAiApply.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendResume(@RequestParam String email, @RequestBody String resume) {
        emailService.sendEmail(email, "Ваше резюме", resume);
        return ResponseEntity.ok("Резюме отправлено на email: " + email);
    }
}