package com.michaelj.domain.converter;

import com.michaelj.domain.dto.PropertyDTO;
import com.michaelj.domain.entity.Property;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PropertyConverter {
    /**实体转DTO*/
    public abstract PropertyDTO toDto(Property entity);
    /**实体转DTO*/
    public abstract List<PropertyDTO> toDtoList(List<Property> entityList);
    /**DTO转实体*/
    public abstract Property toEntity(PropertyDTO dto);
    /**DTO转实体*/
    public abstract List<Property> toEntityList(List<PropertyDTO> dtoList);
}
