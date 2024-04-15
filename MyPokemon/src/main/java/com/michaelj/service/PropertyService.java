package com.michaelj.service;

import com.michaelj.domain.dto.PropertyDTO;
import com.michaelj.domain.entity.Property;

import java.util.List;

public interface PropertyService {
    /**
     * 查询所有属性
     * @return
     */
    List<PropertyDTO> getAllProp();
}
