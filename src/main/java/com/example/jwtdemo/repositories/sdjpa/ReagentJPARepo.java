package com.example.jwtdemo.repositories.sdjpa;

import com.example.jwtdemo.domain.model.Reagent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReagentJPARepo extends JpaRepository<Reagent, Long> {
}
