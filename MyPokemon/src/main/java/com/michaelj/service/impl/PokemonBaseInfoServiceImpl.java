package com.michaelj.service.impl;

import com.michaelj.dao.PokemonBaseInfoDao;
import com.michaelj.domain.PokemonBaseInfo;
import com.michaelj.service.PokemonBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

//TODO 定义 Service bean
@Service
public class PokemonBaseInfoServiceImpl implements PokemonBaseInfoService {
    @Autowired
    private PokemonBaseInfoDao baseInfoDao;

    @Override
    public List<PokemonBaseInfo> getAll() {
        return baseInfoDao.getAll();
    }
}
