package com.michaelj.service;

import cn.hutool.core.collection.CollUtil;
import com.michaelj.dao.ConfigPokeMapper;
import com.michaelj.domain.converter.ConfigPokeConverter;
import com.michaelj.domain.dto.ConfigPokeDTO;
import com.michaelj.domain.entity.ConfigPoke;
import com.michaelj.domain.query.ConfigPokeQuery;
import com.michaelj.infrastructure.constant.BaseConst;
import com.michaelj.infrastructure.constant.PokeExceptionEnum;
import com.michaelj.infrastructure.enums.DatePatternEnum;
import com.michaelj.infrastructure.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ConfigPokeService {
    @Autowired
    private ConfigPokeMapper configPokeMapper;
    @Autowired
    private ConfigPokeConverter configPokeConverter;



    public int selectForCount(ConfigPokeQuery query) {
        return configPokeMapper.selectForCount(query);
    }

    public List<ConfigPoke> selectEntityList(ConfigPokeQuery query) {
        List<ConfigPoke> entityList = configPokeMapper.selectForList(query);
        return entityList;
    }

    public List<ConfigPokeDTO> selectDtoList(ConfigPokeQuery query) {
        List<ConfigPoke> entityList = selectEntityList(query);
        List<ConfigPokeDTO> dtoList = configPokeConverter.toDtoList(entityList);
        return dtoList;
    }

    public ConfigPoke selectOne(ConfigPokeQuery query) {
        List<ConfigPoke> entityList = selectEntityList(query);

        if (CollUtil.isEmpty(entityList)) {
            throw new BusinessException(PokeExceptionEnum.COMMON_100003);
        }
        if (entityList.size() > 1) {
            throw new BusinessException(PokeExceptionEnum.COMMON_100004);
        }

        return entityList.get(BaseConst.FIRST_ITEM);
    }

    public ConfigPoke getById(long id) {
        return selectOne(ConfigPokeQuery.builder().id(id).build());
    }



    public int insertEntity(ConfigPoke entity) {
        LocalDateTime now = LocalDateTime.now();
        String nowFormat = now.format(DatePatternEnum.FULL_SECOND_PATTERN.getFormatter());

        entity.setCreatedAt(nowFormat);
        entity.setUpdatedAt(nowFormat);

        return configPokeMapper.save(entity);
    }


    public int updateEntity(ConfigPoke entity) {
        LocalDateTime now = LocalDateTime.now();
        String nowFormat = now.format(DatePatternEnum.FULL_SECOND_PATTERN.getFormatter());

        entity.setUpdatedAt(nowFormat);

        return configPokeMapper.update(entity);
    }



    public int deleteById(Long id) {
        LocalDateTime now = LocalDateTime.now();
        String nowFormat = now.format(DatePatternEnum.FULL_SECOND_PATTERN.getFormatter());

        return configPokeMapper.deleteById(id, nowFormat);
    }



}
