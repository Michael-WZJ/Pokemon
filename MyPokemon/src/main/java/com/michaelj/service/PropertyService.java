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

    /**
     * 批量更新 属性 css颜色
     * @param propList
     * @return
     */
    int batchUpdateProps(List<PropertyDTO> propList);
}
