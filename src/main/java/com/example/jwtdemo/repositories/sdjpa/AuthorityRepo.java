package com.example.jwtdemo.repositories.sdjpa;

import com.example.jwtdemo.domain.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepo extends JpaRepository<Authority, Long> {
}
