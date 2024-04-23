package com.michaelj.dao;

import com.michaelj.domain.entity.Property;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PropertyMapper {
    /**
     * 查询所有属性
     * @return
     */
    List<Property> getAllProp();

    /**
     * 批量更新 属性 css颜色
     * @param propList
     * @return
     */
    int batchUpdateProps(List<Property> propList);
}
