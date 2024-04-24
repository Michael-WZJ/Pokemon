package com.michaelj.dao;

import com.michaelj.domain.entity.Generation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GenerationMapper {
    /**
     * 查询所有世代
     * @return
     */
    List<Generation> getAllGen();
}
