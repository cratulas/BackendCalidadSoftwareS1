package com.example.backend_bd;

import org.springframework.data.repository.CrudRepository;

// Este código será creado automáticamente por Spring en un Bean llamado userRepository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
