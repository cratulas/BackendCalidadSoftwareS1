package com.example.backend_bd;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import jakarta.servlet.ServletException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(LoginController.class)
@AutoConfigureMockMvc(addFilters = false)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private MyUserDetailsService userDetailsService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private JWTAuthtenticationConfig jwtAuthtenticationConfig;

    @Test
    void loginSuccessful() throws Exception {
        String username = "usuario";
        String password = "1234";
        String encodedPassword = "$2a$10$xxxx";

        UserDetails mockUser = new User(username, encodedPassword, Collections.emptyList());
        when(userDetailsService.loadUserByUsername(username)).thenReturn(mockUser);
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(true);
        when(jwtAuthtenticationConfig.getJWTToken(username)).thenReturn("fake-jwt-token");

        mockMvc.perform(post("/login")
                .param("user", username)
                .param("encryptedPass", password))
               .andExpect(status().isOk())
               .andExpect(content().string("fake-jwt-token"));
    }

    @Test
    void loginInvalidPassword_throws() throws Exception {
        String username = "usuario";
        String password = "incorrecto";
        String encodedPassword = "$2a$10$xxxx";

        UserDetails mockUser = new User(username, encodedPassword, Collections.emptyList());
        when(userDetailsService.loadUserByUsername(username)).thenReturn(mockUser);
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(false);

        assertThrows(ServletException.class, () -> {
            mockMvc.perform(post("/login")
                .param("user", username)
                .param("encryptedPass", password));
        });
    }
}