package com.example.jwtdemo.service.DTO;

import com.example.jwtdemo.domain.DTO.AuthorityDTO;
import com.example.jwtdemo.domain.DTO.AuthorityDTOList;

public interface AuthorityDTOService {
    AuthorityDTOList findAll();

    AuthorityDTOList findAllByUserId(Long id);

    // use this to simplify the JSON output and for systems which implement one authority per user
    AuthorityDTO findFirstByUserId(Long id);
}
