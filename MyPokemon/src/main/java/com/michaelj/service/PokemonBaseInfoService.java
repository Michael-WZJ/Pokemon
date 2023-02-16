package com.michaelj.service;

import com.michaelj.domain.PokemonBaseInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//TODO 开启事务
@Transactional
public interface PokemonBaseInfoService {
    /**
     * 查找全部宝可梦基础信息
     */
    public List<PokemonBaseInfo> getAll();
}
