package com.michaelj.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EvolutionMapper {
    /**
     * 根据编号 查询进化（编号）
     * @param code
     * @return
     */
    String getEvolCode(String code);
}
