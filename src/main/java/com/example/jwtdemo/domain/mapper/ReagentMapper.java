package com.example.jwtdemo.domain.mapper;

import com.example.jwtdemo.domain.DTO.ReagentDTO;
import com.example.jwtdemo.domain.model.Reagent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReagentMapper {
    ReagentMapper INSTANCE = Mappers.getMapper(ReagentMapper.class);

    ReagentDTO reagentToReagentDTO(Reagent reagent);
}
