package com.example.jwtdemo.domain.mapper;

import com.example.jwtdemo.domain.DTO.AuthorityDTO;
import com.example.jwtdemo.domain.security.Authority;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorityMapper {
    AuthorityMapper INSTANCE = Mappers.getMapper(AuthorityMapper.class);

    AuthorityDTO authorityToAuthorityDTO(Authority authority);
}
