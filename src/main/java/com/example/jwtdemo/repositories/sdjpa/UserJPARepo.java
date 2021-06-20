package com.example.jwtdemo.repositories.sdjpa;

import com.example.jwtdemo.domain.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPARepo extends JpaRepository<User, Long> {
        // add custom JPA queries here
        User findByUsername(String username);
    }
