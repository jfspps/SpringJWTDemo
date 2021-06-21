package com.example.jwtdemo.service.DTO;

import com.example.jwtdemo.domain.DTO.ReagentDTO;
import com.example.jwtdemo.domain.DTO.ReagentDTOList;
import com.example.jwtdemo.domain.mapper.ReagentMapper;
import com.example.jwtdemo.repositories.sdjpa.ReagentJPARepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReagentDTOServiceImpl implements ReagentDTOService {

    private final ReagentMapper reagentMapper;
    private final ReagentJPARepo reagentJPARepo;

    // note that the reagentMapper missing bean warning is removed by building the project
    public ReagentDTOServiceImpl(ReagentMapper reagentMapper, ReagentJPARepo reagentJPARepo) {
        this.reagentMapper = reagentMapper;
        this.reagentJPARepo = reagentJPARepo;
    }

    @Override
    public ReagentDTOList findAll() {

        List<ReagentDTO> reagentDTOs = reagentJPARepo
                .findAll()
                .stream()
                .map(reagentMapper::reagentToReagentDTO)
                .collect(Collectors.toList());

        ReagentDTOList reagentDTOList = new ReagentDTOList();
        reagentDTOList.getReagentDTOList().addAll(reagentDTOs);

        return reagentDTOList;
    }

    @Override
    public ReagentDTO findById(Long id) {
        return reagentJPARepo.findById(id)
                .map(reagentMapper::reagentToReagentDTO)
                .orElse(null);
    }
}
