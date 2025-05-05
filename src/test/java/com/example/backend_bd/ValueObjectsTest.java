// src/test/java/com/example/backend_bd/ValueObjectsTest.java
package com.example.backend_bd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValueObjectsTest {

    @Test
    void user_gettersAndSetters_shouldWork() {
        User u = new User();
        u.setId(7);
        u.setUsername("testUser");
        u.setPassword("pw123");

        assertEquals(7, u.getId());
        assertEquals("testUser", u.getUsername());
        assertEquals("pw123", u.getPassword());
    }

    @Test
    void categoria_nombre_getterAndSetter_shouldWork() {
        Categoria c = new Categoria();
        c.setNombre("catX");

        assertEquals("catX", c.getNombre());
    }
}
