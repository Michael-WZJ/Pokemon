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
    List<String> getEvolCodeList(String code);

    /**
     * 根据编号 查询进化前（编号）
     * @param code
     * @return
     */
    List<String> getFilialCodeList(String code);

    /**
     * 批量新增进化关系
     * @param evolutionList
     * @return
     */
    int saveList(List<Evolution> evolutionList);

    /**
     * 修改进化关系
     * 暂时用不上
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
