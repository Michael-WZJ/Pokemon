package com.michaelj.service.impl;

import cn.hutool.core.util.StrUtil;
import com.michaelj.dao.EvolutionMapper;
import com.michaelj.domain.dto.PokemonBaseInfoDTO;
import com.michaelj.domain.entity.Evolution;
import com.michaelj.infrastructure.constant.BaseConst;
import com.michaelj.infrastructure.constant.PokeExceptionEnum;
import com.michaelj.infrastructure.exception.BusinessException;
import com.michaelj.service.EvolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvolutionServiceImpl implements EvolutionService {
    @Autowired
    private EvolutionMapper evolutionMapper;

    /**
     * 根据编号 查询进化（编号）
     *
     * @param code
     * @return
     */
    @Override
    public String getEvolCode(String code) {
        String evolCode = evolutionMapper.getEvolCode(code);
        if (StrUtil.isBlank(evolCode)) {
            evolCode = BaseConst.BLANK;
        }
        return evolCode;
    }

    /**
     * 根据编号 查询进化前（编号）
     *
     * @param code
     * @return
     */
    @Override
    public String getFilialCode(String code) {
        String filialCode = evolutionMapper.getFilialCode(code);
        if (StrUtil.isBlank(filialCode)) {
            filialCode = BaseConst.BLANK;
        }
        return filialCode;
    }

    /**
     * 新增进化关系
     *
     * @param pokemon
     * @return
     */
    @Override
    public boolean saveByPoke(PokemonBaseInfoDTO pokemon) {
        Evolution evolution = Evolution.builder()
                .filialCode(pokemon.getPokeBaseCode())
                .paternalCode(pokemon.getEvolution())
                .build();
        verifyAddEvol(evolution);

        int flag = evolutionMapper.save(evolution);
        return flag > 0;
    }

    /**
     * 修改进化关系
     *
     * @param pokemon
     * @return
     */
    @Override
    public boolean updateByPoke(PokemonBaseInfoDTO pokemon) {
        Evolution evolution = Evolution.builder()
                .filialCode(pokemon.getPokeBaseCode())
                .paternalCode(pokemon.getEvolution())
                .build();
        verifyUpdateEvol(evolution);

        int flag = evolutionMapper.update(evolution);
        return flag > 0;
    }

    /**
     * 根据子代编号删除进化链
     *
     * @param code
     * @return
     */
    @Override
    public boolean deleteByFilialCode(String code) {
        int flag = evolutionMapper.deleteByFilialCode(code);
        return flag > 0;
    }

    public void verifyAddEvol(Evolution evolution) {
        generalVerify(evolution);
        String code = evolution.getFilialCode();

        // 判断子代编号是否重复
        if (!StrUtil.isBlank(evolutionMapper.getEvolCode(code))) {
            throw new BusinessException(PokeExceptionEnum.EVOL_REPEAT_FAIL);
        }
    }

    public void verifyUpdateEvol(Evolution evolution) {
        generalVerify(evolution);
        String code = evolution.getFilialCode();

        // 判断 该编号是否存在
        if (StrUtil.isBlank(evolutionMapper.getEvolCode(code))) {
            throw new BusinessException(PokeExceptionEnum.EVOL_NOT_FOUND_FAIL);
        }
    }

    public void generalVerify(Evolution evolution) {
        if (StrUtil.isBlank(evolution.getFilialCode()) || StrUtil.isBlank(evolution.getPaternalCode())) {
            throw new BusinessException(PokeExceptionEnum.EVOL_PARAM_FAIL);
        }
    }
}
