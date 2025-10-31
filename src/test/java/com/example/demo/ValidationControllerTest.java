/*
Write JUnit 5 tests for ValidationController using @WebMvcTest and MockMvc.

Test cases:
1) GET /api/email/valid?email=test@example.com returns status 200 and JSON containing "valid": true.
2) GET /api/email/valid?email=invalid returns status 200 and JSON containing "valid": false.
3) GET /api/password/quality?password=Hello123! returns status 200 and JSON containing "strength".
4) GET /api/password/quality?password=a returns status 200 and JSON containing "strength": "very weak" or "weak".

Use JSONPath assertions like:  .andExpect(jsonPath("$.valid").value(true))
*/
package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;    

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ValidationController.class)
public class ValidationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testValidEmail() throws Exception {
        mockMvc.perform(get("/api/email/valid").param("email", "test@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(true));
    }

    @Test
    public void testInvalidEmail() throws Exception {
        mockMvc.perform(get("/api/email/valid").param("email", "invalid"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(false));
    }

    @Test
    public void testStrongPassword() throws Exception {
        mockMvc.perform(get("/api/password/quality").param("password", "Hello123!"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.strength").exists());
    }  

    @Test
    public void testWeakPassword() throws Exception {
        mockMvc.perform(get("/api/password/quality").param("password", "a"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.strength").value("very weak"));
    }
}       