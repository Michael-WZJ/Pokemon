package com.michaelj.service;

import com.michaelj.domain.dto.PokemonBaseInfoDTO;

public interface EvolutionService {
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
     * 新增进化关系-根据宝可梦DTO
     * @param pokemon
     * @return
     */
    boolean saveByPoke(PokemonBaseInfoDTO pokemon);

    /**
     * 修改进化关系-根据宝可梦DTO
     * @param pokemon
     * @return
     */
    boolean updateByPoke(PokemonBaseInfoDTO pokemon);

    /**
     * 根据子代编号删除进化链
     * @param code
     * @return
     */
    boolean deleteByFilialCode(String code);
}
