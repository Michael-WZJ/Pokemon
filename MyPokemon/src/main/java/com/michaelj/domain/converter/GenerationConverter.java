package com.michaelj.domain.converter;

import com.michaelj.domain.dto.GenerationDTO;
import com.michaelj.domain.entity.Generation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class GenerationConverter {
    /**实体转DTO*/
    public abstract GenerationDTO toDto(Generation entity);
    /**实体转DTO*/
    public abstract List<GenerationDTO> toDtoList(List<Generation> entityList);
    /**DTO转实体*/
    public abstract Generation toEntity(GenerationDTO dto);
    /**DTO转实体*/
    public abstract List<Generation> toEntityList(List<GenerationDTO> dtoList);
}
