package com.example.jwtdemo.domain.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReagentDTO {

    private Long id;

    private String CAS_id;

    private String chemicalName;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;
}
