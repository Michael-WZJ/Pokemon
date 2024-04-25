package com.michaelj.application;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.michaelj.domain.dto.PokemonBaseInfoDTO;
import com.michaelj.infrastructure.constant.PokeExceptionEnum;
import com.michaelj.infrastructure.exception.BusinessException;
import com.michaelj.service.EvolutionService;
import com.michaelj.service.PokemonBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean updateByPokeWithRules(PokemonBaseInfoDTO pokemon) {
        String evolution = pokemon.getEvolution();
        String code = pokemon.getPokeBaseCode();

        // 如果 进化 为空，则删除进化链
        if (StrUtil.isBlank(evolution)) {
            // todo 直接删除，不需要判断返回值；或者在application层判断 不存在则不删除
            evolutionService.deleteByFilialCode(code);
            return true;
        }

        // 如果进化型不存在，则不能修改
        if (ObjectUtil.isEmpty(baseInfoService.getByCode(evolution))) {
            throw new BusinessException(PokeExceptionEnum.EVOL_PATERNAL_NOT_FOUND_FAIL);
        }

        boolean flag;
        if (StrUtil.isBlank(evolutionService.getEvolCode(code))) {
            // 如果该进化链不存在，则创建进化链
            flag = evolutionService.saveByPoke(pokemon);
        } else {
            // 如果该进化链存在，则更新进化链
            flag = evolutionService.updateByPoke(pokemon);

        }
        return flag;
    }
}
