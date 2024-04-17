package com.michaelj.service.impl;

import com.michaelj.dao.PokemonBaseInfoDao;
import com.michaelj.domain.Code;
import com.michaelj.domain.base.Page;
import com.michaelj.domain.converter.PokemonBaseInfoConverter;
import com.michaelj.domain.dto.PokemonBaseInfoDTO;
import com.michaelj.domain.entity.PokemonBaseInfo;
import com.michaelj.domain.query.PokeBaseInfoQuery;
import com.michaelj.infrastructure.exception.BusinessException;
import com.michaelj.infrastructure.utils.PageExecutor;
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

    @Autowired
    private PokemonBaseInfoConverter baseInfoConverter;

    /**
     * 条件查询宝可梦信息
     *
     * @param query
     * @return
     */
    @Override
    public Page<PokemonBaseInfoDTO> selectPageList(PokeBaseInfoQuery query) {
        return PageExecutor.pagination(query, new PageExecutor.ConvertPageDataLoader<PokemonBaseInfo, PokemonBaseInfoDTO>() {
            @Override
            public List<PokemonBaseInfo> load() {
                return baseInfoDao.selectPageList(query);
            }

            @Override
            public long count() {
                return baseInfoDao.selectPageListCount(query);
            }

            @Override
            public PokemonBaseInfoDTO convert(PokemonBaseInfo entity) {
                return baseInfoConverter.toDto(entity);
            }
        });
    }

    @Override
    public List<PokemonBaseInfo> getAll() {
        return baseInfoDao.getAll();
    }

    @Override
    public PokemonBaseInfoDTO getByCode(String code) {
        return baseInfoConverter.toDto(baseInfoDao.getByCode(code));
    }

    @Override
    public List<PokemonBaseInfo> getByCondition(PokemonBaseInfo pokemon) {
        return baseInfoDao.getByCondition(pokemon);
    }

    @Override
    public Long getPokeCount() {
        return baseInfoDao.getPokeCount();
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
    public boolean update(PokemonBaseInfoDTO pokemon) {
        int flag = baseInfoDao.update(baseInfoConverter.toEntity(pokemon));
        return flag > 0;
    }

    /**
     * 删除宝可梦基本信息
     * @param code
     * @return
     */
    @Override
    public boolean deleteByCode(String code) {
        int flag = baseInfoDao.deleteByCode(code);
        return flag > 0;
    }

    /**
     * 批量删除宝可梦基本信息
     *
     * @param codeList
     * @return
     */
    @Override
    public int deleteByCodeList(List<String> codeList) {
        return baseInfoDao.deleteByCodeList(codeList);
    }
}
