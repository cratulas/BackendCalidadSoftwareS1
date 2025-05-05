package com.example.backend_bd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Verificar valores de Constants")
class ConstantsTest {

    @Test
    void values_areCorrect() {
        assertTrue(Constants.TOKEN_BEARER_PREFIX.startsWith("Bearer "));
        assertEquals("Authorization", Constants.HEADER_AUTHORIZACION_KEY);
        assertTrue(Constants.TOKEN_EXPIRATION_TIME > 0);
    }
}
