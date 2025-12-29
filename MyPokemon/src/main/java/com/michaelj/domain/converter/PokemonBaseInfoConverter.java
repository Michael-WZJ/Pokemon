package com.michaelj.domain.converter;

import com.michaelj.domain.dto.PokemonBaseInfoDTO;
import com.michaelj.domain.entity.PokemonBaseInfo;
import com.michaelj.domain.query.PokeBaseInfoQuery;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PokemonBaseInfoConverter {

    /**实体转DTO*/
    @Mapping(source = "numberOfSeats", target = "seatCount", numberFormat = "￥#.00", defaultValue = "￥0.00")
    @Mapping(target = "gen", ignore = true)
    public abstract PokemonBaseInfoDTO toDto(PokemonBaseInfo entity);
    /**实体转DTO*/
    public abstract List<PokemonBaseInfoDTO> toDtoList(List<PokemonBaseInfo> entityList);
    /**DTO转实体*/
    @InheritInverseConfiguration(name = "toDto")
    public abstract PokemonBaseInfo toEntity(PokemonBaseInfoDTO dto);
    /**DTO转实体*/
    public abstract List<PokemonBaseInfo> toEntityList(List<PokemonBaseInfoDTO> dtoList);

    @InheritConfiguration(name = "toDto")
    @Mapping(target = "gen", ignore = true)
    public abstract PokeBaseInfoQuery toQuery(PokemonBaseInfo entity);
}
