package com.example.jwtdemo.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReagentDTOList {
    private List<ReagentDTO> reagentDTOList;
}
