package com.example.backend_bd;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.backend_bd.Constants.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class JWTAuthorizationFilterTest {

    private JWTAuthorizationFilter filter;
    private HttpServletRequest req;
    private HttpServletResponse res;
    private FilterChain chain;
    private SecretKey key;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        filter = new JWTAuthorizationFilter();
        req   = mock(HttpServletRequest.class);
        res   = mock(HttpServletResponse.class);
        chain = mock(FilterChain.class);
        key   = Keys.hmacShaKeyFor(SUPER_SECRET_KEY.getBytes());
    }

    @Test
    void noHeader_clearsContextAndContinues() throws ServletException, IOException {
        when(req.getHeader(HEADER_AUTHORIZACION_KEY)).thenReturn(null);

        filter.doFilterInternal(req,res,chain);

        verify(chain).doFilter(req,res);
    }

    @Test
    void validTokenWithAuthorities_setsAuthenticationAndContinues() throws ServletException, IOException {
        Map<String,Object> claims = new HashMap<>();
        claims.put("authorities", List.of("ROLE_USER"));

        String jwt = Jwts.builder()
                .setClaims(claims)
                .setSubject("mauri")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1_000))
                .signWith(key)
                .compact();
        String bearer = TOKEN_BEARER_PREFIX + jwt;

        when(req.getHeader(HEADER_AUTHORIZACION_KEY)).thenReturn(bearer);

        filter.doFilterInternal(req,res,chain);

        verify(chain).doFilter(req,res);
    }

    @Test
    void expiredToken_returnsForbidden() throws ServletException, IOException {
        String jwt = Jwts.builder()
                .setSubject("mauro")
                .setIssuedAt(new Date(System.currentTimeMillis() - 5_000))
                .setExpiration(new Date(System.currentTimeMillis() - 1_000))
                .signWith(key)
                .compact();
        when(req.getHeader(HEADER_AUTHORIZACION_KEY)).thenReturn(TOKEN_BEARER_PREFIX + jwt);

        filter.doFilterInternal(req,res,chain);

        verify(res).setStatus(HttpServletResponse.SC_FORBIDDEN);
        verify(res).sendError(eq(HttpServletResponse.SC_FORBIDDEN), contains("JWT"));
    }
}
