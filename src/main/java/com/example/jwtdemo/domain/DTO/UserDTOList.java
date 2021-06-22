package com.example.jwtdemo.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOList {
    // initialise with new ArrayList otherwise getReagentDTOList returns NPE
    private List<UserDTO> userDTOList = new ArrayList<>();
}
