package com.MyAiApply.MyAiApply.Controller;

import com.MyAiApply.MyAiApply.model.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationRestControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testAddApplication() {
        Application application = new Application();
        application.setJobTitle("Developer");
        application.setCompany("MyCompany");
        application.setStatus("Active");

        ResponseEntity<String> response = restTemplate.postForEntity("/api/applications/add", application, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Заявка добавлена", response.getBody());
    }
}