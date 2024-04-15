package com.michaelj.service.impl;

import com.michaelj.dao.PropertyMapper;
import com.michaelj.domain.converter.PropertyConverter;
import com.michaelj.domain.dto.PropertyDTO;
import com.michaelj.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyMapper propertyMapper;

    @Autowired
    private PropertyConverter propertyConverter;

    /**
     * 查询所有属性
     *
     * @return
     */
    @Override
    public List<PropertyDTO> getAllProp() {
        return propertyConverter.toDtoList(propertyMapper.getAllProp());
    }
}
