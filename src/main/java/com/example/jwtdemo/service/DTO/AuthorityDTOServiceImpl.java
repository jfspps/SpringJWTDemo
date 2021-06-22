package com.example.jwtdemo.service.DTO;

import com.example.jwtdemo.domain.DTO.AuthorityDTO;
import com.example.jwtdemo.domain.DTO.AuthorityDTOList;
import com.example.jwtdemo.domain.mapper.AuthorityMapper;
import com.example.jwtdemo.domain.security.Authority;
import com.example.jwtdemo.domain.security.User;
import com.example.jwtdemo.exception.NotFoundException;
import com.example.jwtdemo.repositories.sdjpa.AuthorityJPARepo;
import com.example.jwtdemo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorityDTOServiceImpl implements AuthorityDTOService{

    private final AuthorityJPARepo authorityJPARepo;
    private final AuthorityMapper authorityMapper;
    private final UserService userService;

    // note that the authorityMapper missing bean warning is removed by building the project
    public AuthorityDTOServiceImpl(AuthorityJPARepo authorityJPARepo, AuthorityMapper authorityMapper, UserService userService) {
        this.authorityJPARepo = authorityJPARepo;
        this.authorityMapper = authorityMapper;
        this.userService = userService;
    }

    @Override
    public AuthorityDTOList findAll() {
        List<AuthorityDTO> authorityDTOs = authorityJPARepo
                .findAll()
                .stream()
                .map(authorityMapper::authorityToAuthorityDTO)
                .collect(Collectors.toList());

        AuthorityDTOList authorityDTOList = new AuthorityDTOList();
        authorityDTOList.getAuthorityDTOList().addAll(authorityDTOs);

        return authorityDTOList;
    }

    @Override
    public AuthorityDTOList findAllByUserId(Long userID) {
        User user = userService.findById(userID);

        if (user == null){
            throw new NotFoundException("User not found; cannot build authority list");
        }

        Set<Authority> authorityList = user.getAuthorities();
        List<AuthorityDTO> authorityDTOs = authorityList
                .stream()
                .map(authorityMapper::authorityToAuthorityDTO)
                .collect(Collectors.toList());

        AuthorityDTOList authorityDTOList = new AuthorityDTOList();
        authorityDTOList.getAuthorityDTOList().addAll(authorityDTOs);

        return authorityDTOList;
    }

    @Override
    public AuthorityDTO findFirstByUserId(Long userID) {
        User user = userService.findById(userID);

        if (user == null){
            throw new NotFoundException("User not found; cannot build authority list");
        }

        Set<Authority> authorityList = user.getAuthorities();
        AuthorityDTO nullAuthority = new AuthorityDTO();
        nullAuthority.setRole("None on file");

         return authorityList
                .stream()
                .findFirst()
                .map(authorityMapper::authorityToAuthorityDTO)
                .orElse(nullAuthority);
    }
}
