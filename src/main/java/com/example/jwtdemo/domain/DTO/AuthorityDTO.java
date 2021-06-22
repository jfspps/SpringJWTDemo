package com.example.jwtdemo.domain.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDateTime;

// always use lowercase (camel-case is fine) at start of field names otherwise Jackson JSON sort does not work!
@Data
@JsonPropertyOrder({"id", "role", "createdOn", "updatedOn"})
public class AuthorityDTO {

    private Long id;

    private String role;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;
}
