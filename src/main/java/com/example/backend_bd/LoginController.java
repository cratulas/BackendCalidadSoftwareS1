package com.example.backend_bd;

import com.example.backend_bd.User;
import com.example.backend_bd.JWTAuthtenticationConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class LoginController {

    @Autowired
    JWTAuthtenticationConfig jwtAuthtenticationConfig;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("login")
    public String login(
            @RequestParam("user") String username,
            @RequestParam("encryptedPass") String encryptedPass) {


        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (!passwordEncoder.matches(encryptedPass, userDetails.getPassword())) {
            throw new RuntimeException("Invalid login");
        }
        

        String token = jwtAuthtenticationConfig.getJWTToken(username);

        return token;

    }

}