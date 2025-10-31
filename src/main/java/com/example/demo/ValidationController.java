/*
Prompt I used to generate this controller with GitHub Copilot:

Write a Spring Boot REST controller with two endpoints for validating password quality and email format.
Named ValidationController.java in the package com.example.demo.
Base path: /api

Endpoint 1:
GET /api/password/quality
Takes a query parameter "password" and returns JSON with:
- hasLower
- hasUpper
- hasDigit
- hasSymbol
- length 
- score (0-5)
- strength (very weak, weak, medium, strong, very strong)

Endpoint 2:
GET /api/email/valid
Takes a query parameter "email" and:
- validates format using regex
- returns JSON with email, valid, and reason
*/

package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class ValidationController {

    @GetMapping("/password/quality")
    public Map<String, Object> checkPasswordQuality(@RequestParam String password) {
        int score = 0;

        boolean hasLower = password.chars().anyMatch(Character::isLowerCase);
        boolean hasUpper = password.chars().anyMatch(Character::isUpperCase);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);
        boolean hasSymbol = password.chars().anyMatch(ch -> !Character.isLetterOrDigit(ch));

        if (password.length() >= 8) score++;
        if (hasLower) score++;
        if (hasUpper) score++;
        if (hasDigit) score++;
        if (hasSymbol) score++;

        String strength;
        switch (score) {
            case 0:
            case 1:
                strength = "very weak";
                break;
            case 2:
                strength = "weak";
                break;
            case 3:
                strength = "medium";
                break;
            case 4:
                strength = "strong";
                break;
            case 5:
                strength = "very strong";
                break;
            default:
                strength = "unknown";
        }

        Map<String, Object> response = new HashMap<>();
        response.put("length", password.length());
        response.put("hasLowercase", hasLower);
        response.put("hasUppercase", hasUpper);
        response.put("hasDigit", hasDigit);
        response.put("hasSymbol", hasSymbol);
        response.put("score", score);
        response.put("strength", strength);

        return response;
    }

    @GetMapping("/email/valid")
    public Map<String, Object> validateEmail(@RequestParam String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        boolean isValid = pattern.matcher(email).matches();
        String reason = isValid ? "Valid email format" : "Invalid email format";

        Map<String, Object> response = new HashMap<>();
        response.put("email", email);
        response.put("valid", isValid);
        response.put("reason", reason);

        return response;
    }
}