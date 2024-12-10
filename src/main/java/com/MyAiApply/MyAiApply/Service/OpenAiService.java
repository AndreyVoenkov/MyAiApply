package com.MyAiApply.MyAiApply.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenAiService {

    private final String API_URL = "https://api.openai.com/v1/completions";

    @Value("${openai.api.key}")
    private String apiKey;

    public String generateCoverLetter(String jobTitle, String company, String userName) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey); // Используем apiKey из переменной окружения

        String requestBody = "{\"model\": \"text-davinci-003\", \"prompt\": \"Напиши сопроводительное письмо для вакансии '" + jobTitle + "' в компании '" + company + "' от имени '" + userName + "'.\", \"max_tokens\": 300}";

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(API_URL, entity, String.class);

        return response.getBody();
    }
}