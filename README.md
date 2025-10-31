# Lab 6

Lab 6: REST endpoints with a Spring Java system.
This lab implements 2 REST API endpoints using Spring Boot that analyze:
1. Password Strength
2. Email Validity

## To Run Locally
```bash
./mvnw -q test            # Run tests
./mvnw spring-boot:run    # Start the server
```
## Examples
```bash
curl "http://localhost:8080/api/password/quality?password=L0ng%26Strong!"
curl "http://localhost:8080/api/email/valid?email=user@example.com"
```

## Endpoints
- GET /api/password/quality
- GET /api/email/valid

## Consumes
- password (query param)
- email (query param)

## Returns
- password, score, strength, length, hasLower, hasUpper, hasDigit, hasSymbol
- email, valid, reason

## Description of what I did:
For this lab, I created a Spring Boot Rest API with 2 endpoints. The first endpoint analyzes the strength of a password, checking its length, lowercase and uppercase letters, digits, and symbols. It will then calculate a score and rate how weak or strong the password is.
The second endpoint validates whether an email address follows the standard format and will return whether it's valid or not along with reasoning.
I used GitHub Copilot to help write the controller and unit test code. Prompts are included on the top of each file of ValidationController.java and ValidationControllerTest.java.
