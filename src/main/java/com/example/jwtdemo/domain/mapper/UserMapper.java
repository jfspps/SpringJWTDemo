package com.example.jwtdemo.domain.mapper;

import com.example.jwtdemo.domain.DTO.UserDTO;
import com.example.jwtdemo.domain.security.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);
}
