// src/test/java/com/example/backend_bd/SecuredControllerTest.java
package com.example.backend_bd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SecuredControllerTest {

    private SecuredController controller;

    @BeforeEach
    void setUp() {
        controller = new SecuredController();
    }

    @Test
    void greetings_withExplicitName_shouldReturnHelloName() {
        String resp = controller.greetings("Mauri");
        assertEquals("Hello {Mauri}", resp);
    }

    @Test
    void greetings_withDefaultName_shouldReturnHelloWorld() {
        String resp = controller.greetings("World");
        assertEquals("Hello {World}", resp);
    }
}
