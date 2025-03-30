package com.example.backend_bd;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BackendBdApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendBdApplication.class, args);
	}

	@Bean
	public CommandLineRunner initUsers(UserRepository userRepository, PasswordEncoder encoder) {
		return args -> {
			if (userRepository.count() == 0) {
				userRepository.save(new User("gamer1", "gamer1@example.com", encoder.encode("1234")));
				userRepository.save(new User("gamer2", "gamer2@example.com", encoder.encode("abcd")));
				userRepository.save(new User("gamer3", "gamer3@example.com", encoder.encode("pass")));
				System.out.println("✅ Usuarios creados correctamente.");
			}
		};
	}
}
