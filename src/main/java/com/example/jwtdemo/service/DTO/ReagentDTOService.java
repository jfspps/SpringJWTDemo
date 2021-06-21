package com.example.jwtdemo.service.DTO;

import com.example.jwtdemo.domain.DTO.ReagentDTO;
import com.example.jwtdemo.domain.DTO.ReagentDTOList;

public interface ReagentDTOService {

    ReagentDTOList findAll();

    ReagentDTO findById(Long id);
}
