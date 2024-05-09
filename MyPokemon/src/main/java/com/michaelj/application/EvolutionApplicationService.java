package com.michaelj.application;

import cn.hutool.core.util.StrUtil;
import com.michaelj.domain.dto.PokemonBaseInfoDTO;
import com.michaelj.domain.entity.PokemonBaseInfo;
import com.michaelj.infrastructure.constant.BaseConst;
import com.michaelj.infrastructure.constant.PokeExceptionEnum;
import com.michaelj.infrastructure.exception.BusinessException;
import com.michaelj.service.EvolutionService;
import com.michaelj.service.PokemonBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EvolutionApplicationService {
    @Autowired
    private EvolutionService evolutionService;

    @Autowired
    private PokemonBaseInfoService baseInfoService;

    /**
     * 修改进化关系
     *
     * @param pokemon
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean updateByPokeWithRules(PokemonBaseInfoDTO pokemon) {
        String evolution = pokemon.getEvolution();
        String code = pokemon.getPokeBaseCode();

        // 如果 进化 为空，则删除进化链
        if (StrUtil.isBlank(evolution)) {
            // todo 直接删除，不需要判断返回值；或者在application层判断 不存在则不删除
            evolutionService.deleteByFilialCode(code);
            return true;
        }

        List<String> evolutionList = List.of(evolution.split(BaseConst.SPLIT_CAESURA));
        List<PokemonBaseInfoDTO> evolPokeList = baseInfoService.getByCodeList(evolutionList);
        // 如果进化型不存在，则不能修改
        if (evolutionList.size() != evolPokeList.size()) {
            Set<String> evolPokeSet = evolPokeList.stream()
                    .map(PokemonBaseInfoDTO::getPokeBaseCode)
                    .collect(Collectors.toSet());

            // 找到不存在的 进化型编号
            String missingEvols = evolutionList.stream()
                    .filter(e -> !evolPokeSet.contains(e))
                    .collect(Collectors.joining(BaseConst.SPLIT_CAESURA));

            throw new BusinessException(PokeExceptionEnum.EVOL_PATERNAL_NOT_FOUND_FAIL, missingEvols);
        }

        // 更新进化关系，先删除 再新增
        boolean flag;
        evolutionService.deleteByFilialCode(code);
        // save方法会 去重!!!
        flag = evolutionService.saveByPoke(pokemon);
        return flag;
    }
}
