package com.example.jwtdemo.domain.security;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
public class Authority implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String authority;

    public static final String ADMIN = "ROLE_ADMIN";
    public static final String USER = "ROLE_USER";

    @Version
    private Long version;

    private String serialNumber;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    @PrePersist
    public void setCreate_Date(){
        this.setCreatedOn(LocalDateTime.now());
    }

    @PreUpdate
    public void doSomethingBeforeUpdating(){
        this.setUpdatedOn(LocalDateTime.now());
    }

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;
}