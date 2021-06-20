package com.example.jwtdemo.domain.model;

import lombok.*;
import org.springframework.data.annotation.Version;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reagent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    @NotBlank(message = "Cannot use a blank CAS ID")
    private String CAS_id;

    @NotBlank(message = "Cannot use a blank chemical name")
    private String chemicalName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reagent reagent = (Reagent) o;
        return CAS_id.equals(reagent.CAS_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CAS_id);
    }
}