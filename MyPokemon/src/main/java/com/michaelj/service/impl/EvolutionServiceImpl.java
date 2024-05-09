package com.michaelj.service.impl;

import cn.hutool.core.collection.CollUtil;
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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public String getEvolCodesStr(String code) {
        List<String> evolCodeList = evolutionMapper.getEvolCodeList(code);

        if (CollUtil.isEmpty(evolCodeList)) {
            return BaseConst.BLANK;
        }
        return String.join(BaseConst.SPLIT_CAESURA, evolCodeList);
    }

    /**
     * 根据编号 查询进化前（编号）
     *
     * @param code
     * @return
     */
    @Override
    public String getFilialCode(String code) {
        List<String> filialCodeList = evolutionMapper.getFilialCodeList(code);

        if (CollUtil.isEmpty(filialCodeList)) {
            return BaseConst.BLANK;
        }
        return String.join(BaseConst.SPLIT_CAESURA, filialCodeList);
    }

    /**
     * 新增进化关系
     *
     * @param pokemon
     * @return
     */
    @Override
    public boolean saveByPoke(PokemonBaseInfoDTO pokemon) {
        String filialCode = pokemon.getPokeBaseCode();
        // 进化列表 去重!!!
        List<String> evols = List.of(pokemon.getEvolution().split(BaseConst.SPLIT_CAESURA));

        List<Evolution> evolutionList = evols.stream().map(
                evol -> Evolution.builder()
                        .filialCode(filialCode)
                        .paternalCode(evol)
                        .build()
        ).toList();

        verifyAddEvolList(evolutionList);

        int flag = evolutionMapper.saveList(evolutionList);
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
    public int deleteByFilialCode(String code) {
        return evolutionMapper.deleteByFilialCode(code);
    }

    public void verifyAddEvolList(List<Evolution> evolutionList) {
        evolutionList.forEach(this::verifyAddEvol);
    }

    public void verifyAddEvol(Evolution evolution) {
        generalVerify(evolution);
    }

    public void verifyUpdateEvol(Evolution evolution) {
        generalVerify(evolution);
        String code = evolution.getFilialCode();

        // todo 进化 判断 该编号是否存在
//        if (StrUtil.isBlank(evolutionMapper.getEvolCode(code))) {
//            throw new BusinessException(PokeExceptionEnum.EVOL_NOT_FOUND_FAIL);
//        }
    }

    public void generalVerify(Evolution evolution) {
        if (StrUtil.isBlank(evolution.getFilialCode()) || StrUtil.isBlank(evolution.getPaternalCode())) {
            throw new BusinessException(PokeExceptionEnum.EVOL_PARAM_FAIL);
        }
    }
}
