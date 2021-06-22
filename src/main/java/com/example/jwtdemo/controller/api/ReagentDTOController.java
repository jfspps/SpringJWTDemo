package com.example.jwtdemo.controller.api;

import com.example.jwtdemo.domain.DTO.ReagentDTO;
import com.example.jwtdemo.domain.DTO.ReagentDTOList;
import com.example.jwtdemo.exception.NotFoundException;
import com.example.jwtdemo.service.DTO.ReagentDTOService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping(ReagentDTOController.ROOT_URL)
public class ReagentDTOController {

    public static final String ROOT_URL = "/api/reagent";

    private final ReagentDTOService reagentDTOService;

    public ReagentDTOController(ReagentDTOService reagentDTOService) {
        this.reagentDTOService = reagentDTOService;
    }

    /**
     * Retrieves a list of all Reagent DTOs on file; throws NotFoundException if nothing found
     * @return DTO list of Reagents
     */
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ReagentDTOList getAllReagents(){
        ReagentDTOList list = new ReagentDTOList();
        List<ReagentDTO> reagentDTOList = reagentDTOService.findAll().getReagentDTOList();

        if (reagentDTOList.isEmpty()){
            throw new NotFoundException("No reagents on file");
        }

        list.getReagentDTOList().addAll(reagentDTOList);
        return list;
    }

    /**
     * Retrieves a Reagent DTO with the given ID; throws NotFoundException if nothing found
     * @param ID Reagent (persistence) ID
     * @return Reagent DTO
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReagentDTO findReagentById(@PathVariable("id") Long ID){
        ReagentDTO reagentDTO = reagentDTOService.findById(ID);

        if (reagentDTO == null){
            throw new NotFoundException("Reagent not found");
        }

        return reagentDTO;
    }
}
