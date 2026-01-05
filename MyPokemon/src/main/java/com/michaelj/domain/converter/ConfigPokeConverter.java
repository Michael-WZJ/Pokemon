package com.michaelj.domain.converter;

import com.michaelj.domain.dto.ConfigPokeDTO;
import com.michaelj.domain.entity.ConfigPoke;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ConfigPokeConverter {
    /**实体转DTO*/
    public abstract ConfigPokeDTO toDto(ConfigPoke entity);
    /**实体转DTO*/
    public abstract List<ConfigPokeDTO> toDtoList(List<ConfigPoke> entityList);
    /**DTO转实体*/
    public abstract ConfigPoke toEntity(ConfigPokeDTO dto);
    /**DTO转实体*/
    public abstract List<ConfigPoke> toEntityList(List<ConfigPokeDTO> dtoList);

}
