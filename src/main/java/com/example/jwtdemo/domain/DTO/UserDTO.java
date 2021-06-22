package com.example.jwtdemo.domain.DTO;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

// always use lowercase (camel-case is fine) at start of field names otherwise Jackson JSON sort does not work!
@Data
@JsonPropertyOrder({"id", "username", "authorityDTOList", "createdOn", "updatedOn", "enabled", "accountNonExpired", "accountNonLocked", "credentialsNonExpired"})
public class UserDTO {

    private Long id;

    private String username;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    private Boolean enabled;

    private Boolean accountNonExpired;

    private Boolean accountNonLocked;

    private Boolean credentialsNonExpired;

    @JsonProperty("authorities")
    private AuthorityDTOList authorityDTOList;
}
