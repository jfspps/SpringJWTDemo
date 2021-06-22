package com.example.jwtdemo.domain.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReagentDTOList {
    // initialise with new ArrayList otherwise getReagentDTOList returns NPE
    @JsonProperty("reagents")
    private List<ReagentDTO> reagentDTOList = new ArrayList<>();
}
