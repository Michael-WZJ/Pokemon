package com.michaelj.dao;

import com.michaelj.domain.entity.Evolution;
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

    /**
     * 根据编号 查询进化前（编号）
     * @param code
     * @return
     */
    String getFilialCode(String code);

    /**
     * 新增进化关系
     * @param evolution
     * @return
     */
    int save(Evolution evolution);

    /**
     * 修改进化关系
     * @param evolution
     * @return
     */
    int update(Evolution evolution);

    /**
     * 根据子代编号删除进化链
     * @param code
     * @return
     */
    int deleteByFilialCode(String code);
}
