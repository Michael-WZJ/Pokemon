package com.michaelj.service.impl;

import cn.hutool.core.util.StrUtil;
import com.michaelj.dao.EvolutionMapper;
import com.michaelj.infrastructure.constant.BaseConst;
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
}
