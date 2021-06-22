package com.example.jwtdemo.service.DTO;

import com.example.jwtdemo.domain.DTO.UserDTO;
import com.example.jwtdemo.domain.DTO.UserDTOList;

public interface UserDTOService {

    UserDTOList findAll();

    UserDTO findById(Long id);
}
