package com.example.jwtdemo.service.DTO;

import com.example.jwtdemo.domain.DTO.UserDTO;
import com.example.jwtdemo.domain.DTO.UserDTOList;
import com.example.jwtdemo.domain.mapper.UserMapper;
import com.example.jwtdemo.repositories.sdjpa.UserJPARepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDTOServiceImpl implements UserDTOService{

    private final UserJPARepo userJPARepo;
    private final UserMapper userMapper;

    // note that the userMapper missing bean warning is removed by building the project
    public UserDTOServiceImpl(UserJPARepo userJPARepo, UserMapper userMapper) {
        this.userJPARepo = userJPARepo;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTOList findAll() {
        List<UserDTO> userDTOs = userJPARepo
                .findAll()
                .stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());

        UserDTOList userDTOList = new UserDTOList();
        userDTOList.getUserDTOList().addAll(userDTOs);

        return userDTOList;
    }

    @Override
    public UserDTO findById(Long id) {
        return userJPARepo.findById(id)
                .map(userMapper::userToUserDTO)
                .orElse(null);
    }
}
