package com.example.backend_bd;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

import static org.junit.jupiter.api.Assertions.*;

class JWTAuthtenticationConfigTest {

    private static final String USER = "mauri";
    private final JWTAuthtenticationConfig config = new JWTAuthtenticationConfig();
    private final SecretKey key = Keys.hmacShaKeyFor(Constants.SUPER_SECRET_KEY.getBytes());

    @Test
    void getJWTToken_validToken_parsesSubjectAndAuthorities() {
        String bearer = config.getJWTToken(USER);

        assertTrue(bearer.startsWith(Constants.TOKEN_BEARER_PREFIX),
                () -> "El token debe empezar con " + Constants.TOKEN_BEARER_PREFIX);

        String jwt = bearer.substring(Constants.TOKEN_BEARER_PREFIX.length());
        Jws<Claims> parsed = Jwts.parser()
                                 .verifyWith(key)
                                 .build()
                                 .parseSignedClaims(jwt);

        Claims claims = parsed.getPayload();
        assertEquals(USER, claims.getSubject(), "El subject debe ser el nombre de usuario");
        assertNotNull(claims.get("authorities"), "Debe existir el claim 'authorities'");
        assertTrue(claims.get("authorities") instanceof java.util.List,
                "authorities debe ser una lista");
    }
}