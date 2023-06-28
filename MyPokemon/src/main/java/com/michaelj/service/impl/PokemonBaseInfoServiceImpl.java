package com.michaelj.service.impl;

import com.michaelj.dao.PokemonBaseInfoDao;
import com.michaelj.domain.Code;
import com.michaelj.domain.PokemonBaseInfo;
import com.michaelj.exception.BusinessException;
import com.michaelj.service.PokemonBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO 定义 Service bean
@Service
public class PokemonBaseInfoServiceImpl implements PokemonBaseInfoService {
    @Autowired
    private PokemonBaseInfoDao baseInfoDao;

    @Override
    public List<PokemonBaseInfo> getAll() {
        return baseInfoDao.getAll();
    }

    @Override
    public PokemonBaseInfo getByCode(String code) {
        return baseInfoDao.getByCode(code);
    }

    @Override
    public List<PokemonBaseInfo> getByCondition(PokemonBaseInfo pokemon) {
        return baseInfoDao.getByCondition(pokemon);
    }

    @Override
    public boolean save(PokemonBaseInfo pokemon) {
        try {
            int flag = baseInfoDao.save(pokemon);
            return flag > 0;
        } catch (DataAccessException e) {
            //TODO 如何处理SQL异常
            throw new BusinessException(Code.PROJECT_BUSINESS_ERR, "SQL异常 ：   " + e.getMessage(), e);
            // springboot包装了所有DB的异常
            // https://www.codenong.com/cs106502042/
        }
    }

    @Override
    public boolean update(PokemonBaseInfo pokemon) {
        int flag = baseInfoDao.update(pokemon);
        return flag > 0;
    }

    @Override
    public boolean deleteByCode(String code) {
        int flag = baseInfoDao.deleteByCode(code);
        return flag > 0;
    }


}
