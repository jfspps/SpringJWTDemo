package com.example.jwtdemo.domain.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDateTime;

// always use lowercase (camel-case is fine) at start of field names otherwise Jackson JSON sort does not work!
@Data
@JsonPropertyOrder({"id", "cas_id", "chemicalName", "createdOn", "updatedOn"})
public class ReagentDTO {

    private Long id;

    private String cas_id;

    private String chemicalName;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;
}
