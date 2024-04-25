package com.michaelj.application;

import cn.hutool.core.util.StrUtil;
import com.michaelj.domain.dto.PokemonBaseInfoDTO;
import com.michaelj.service.EvolutionService;
import com.michaelj.service.PokemonBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PokeBaseInfoApplicationService {
    @Autowired
    private PokemonBaseInfoService baseInfoService;

    @Autowired
    private EvolutionService evolutionService;

    @Autowired
    private EvolutionApplicationService evolutionApplicationService;

    /**
     * 按code查询
     * @param code
     * @return
     */
    public PokemonBaseInfoDTO getByCode(String code) {
        PokemonBaseInfoDTO baseInfoDTO = baseInfoService.getByCode(code);
        // 获取进化编号
        baseInfoDTO.setEvolution(evolutionService.getEvolCode(code));
        // 获取进化前编号
        baseInfoDTO.setFilial(evolutionService.getFilialCode(code));
        return baseInfoDTO;
    }

    /**
     * 新增宝可梦基本信息
     * @param pokemon
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean save(PokemonBaseInfoDTO pokemon) {
        boolean flag = baseInfoService.save(pokemon);
        if (!flag) {
            return false;
        }

        if (StrUtil.isBlank(pokemon.getEvolution())) {
            // 如果 进化 为空，则不需要添加进化链
            return true;
        }
        // 如果 进化 不为空，则添加进化链
        return evolutionService.saveByPoke(pokemon);
    }

    /**
     * 修改宝可梦基本信息
     * @param pokemon
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean update(PokemonBaseInfoDTO pokemon) {
        boolean flag = baseInfoService.update(pokemon);
        if (!flag) {
            return false;
        }

        return evolutionApplicationService.updateByPokeWithRules(pokemon);
    }

    /**
     * 删除宝可梦基本信息
     * @param code
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByCode(String code) {
        boolean flag = baseInfoService.deleteByCode(code);
        if (!flag) {
            return false;
        }

        // todo 直接删除，不需要判断返回值；或者在application层判断 不存在则不删除
        // 同时删除进化关系
        evolutionService.deleteByFilialCode(code);
        return true;
    }
}
