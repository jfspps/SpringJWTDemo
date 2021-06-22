package com.example.jwtdemo.controller.api;

import com.example.jwtdemo.domain.DTO.UserDTO;
import com.example.jwtdemo.domain.DTO.UserDTOList;
import com.example.jwtdemo.exception.NotFoundException;
import com.example.jwtdemo.service.DTO.AuthorityDTOService;
import com.example.jwtdemo.service.DTO.UserDTOService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping(UserDTOController.ROOT_URL)
public class UserDTOController {

    public static final String ROOT_URL = "/api/user";

    private final UserDTOService userDTOService;
    private final AuthorityDTOService authorityDTOService;

    public UserDTOController(UserDTOService userDTOService, AuthorityDTOService authorityDTOService) {
        this.userDTOService = userDTOService;
        this.authorityDTOService = authorityDTOService;
    }

    /**
     * Retrieves a list of all UserDTOs on file; throws NotFoundException if nothing found
     * @return DTO list of Users
     */
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public UserDTOList getAllUsers(){
        UserDTOList list = new UserDTOList();
        List<UserDTO> userDTOs = userDTOService.findAll().getUserDTOList();

        if (userDTOs.isEmpty()){
            throw new NotFoundException("No users on file");
        }

        // add authorities to each UserDTO
        for (UserDTO userDTO : userDTOs){
            userDTO.setAuthorityDTOList(authorityDTOService.findAllByUserId(userDTO.getId()));
        }

        list.getUserDTOList().addAll(userDTOs);
        return list;
    }

    /**
     * Retrieves a UserDTO with the given ID; throws NotFoundException if nothing found
     * @param ID User (persistence) ID
     * @return UserDTO
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO findUserById(@PathVariable("id") Long ID){
        UserDTO userDTO = userDTOService.findById(ID);

        if (userDTO == null){
            throw new NotFoundException("User not found");
        }

        userDTO.setAuthorityDTOList(authorityDTOService.findAllByUserId(userDTO.getId()));
        return userDTO;
    }
}
