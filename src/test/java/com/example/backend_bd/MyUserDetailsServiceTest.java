package com.example.backend_bd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MyUserDetailsServiceTest {

    private MyUserDetailsService service;
    private UserRepository repoMock;

    @BeforeEach
    void setup() {
        repoMock = mock(UserRepository.class);
        service = new MyUserDetailsService();
        // Inyectamos el mock en el bean
        ReflectionTestUtils.setField(service, "userRepository", repoMock);
    }

    @Test
    void loadUserByUsername_whenExists_returnsUser() {
        User u = new User();
        u.setUsername("pepe");
        u.setPassword("secreto");
        when(repoMock.findByUsername("pepe")).thenReturn(u);

        var detalles = service.loadUserByUsername("pepe");
        assertSame(u, detalles);
    }

    @Test
    void loadUserByUsername_whenNotExists_throwsException() {
        when(repoMock.findByUsername("noExiste")).thenReturn(null);
        assertThrows(
            UsernameNotFoundException.class,
            () -> service.loadUserByUsername("noExiste"),
            "Debe lanzar UsernameNotFoundException si el usuario no está"
        );
    }

    @Test
    void passwordEncoderBean_isBCrypt() {
        PasswordEncoder encoder = service.passwordEncoder();
        assertNotNull(encoder);
        String raw = "abc123";
        String hash = encoder.encode(raw);
        assertTrue(encoder.matches(raw, hash));
    }
}
