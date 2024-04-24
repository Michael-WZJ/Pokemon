package com.michaelj.domain.converter;

import com.michaelj.domain.dto.PokemonBaseInfoDTO;
import com.michaelj.domain.entity.PokemonBaseInfo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PokemonBaseInfoConverter {
    /**实体转DTO*/
    public abstract PokemonBaseInfoDTO toDto(PokemonBaseInfo entity);
    /**实体转DTO*/
    public abstract List<PokemonBaseInfoDTO> toDtoList(List<PokemonBaseInfo> entityList);
    /**DTO转实体*/
    public abstract PokemonBaseInfo toEntity(PokemonBaseInfoDTO dto);
    /**DTO转实体*/
    public abstract List<PokemonBaseInfo> toEntityList(List<PokemonBaseInfoDTO> dtoList);
}
