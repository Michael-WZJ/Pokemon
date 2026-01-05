package com.michaelj.application;

import com.michaelj.domain.converter.ConfigPokeConverter;
import com.michaelj.domain.dto.ConfigPokeDTO;
import com.michaelj.domain.entity.ConfigPoke;
import com.michaelj.domain.query.ConfigPokeQuery;
import com.michaelj.infrastructure.constant.BaseConst;
import com.michaelj.infrastructure.constant.PokeExceptionEnum;
import com.michaelj.infrastructure.exception.BusinessException;
import com.michaelj.service.ConfigPokeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class ConfigPokeApplicationService {
    @Autowired
    private ConfigPokeConverter configPokeConverter;

    @Autowired
    private ConfigPokeService configPokeService;



    public boolean save(ConfigPokeDTO configPokeDTO) {
        String code = configPokeDTO.getCode();
        Long parentId = configPokeDTO.getParentId();

        int cnt = configPokeService.selectForCount(ConfigPokeQuery.builder()
                .code(code)
                .build());
        if (cnt > 0) {
            throw new BusinessException(PokeExceptionEnum.COMMON_100002);
        }


        ConfigPoke parent = configPokeService.getById(parentId);
        String parentPath = parent.getCurrentPath();
        String currentPath = parentPath + BaseConst.SPLIT_DOT + code;
        configPokeDTO.setParentPath(parentPath);
        configPokeDTO.setCurrentPath(currentPath);

        int flag = configPokeService.insertEntity(configPokeConverter.toEntity(configPokeDTO));
        return flag > 0;
    }


    public boolean update(ConfigPokeDTO configPokeDTO) {
        String value = configPokeDTO.getValue();
        String description = configPokeDTO.getDescription();
        Long id = configPokeDTO.getId();

        ConfigPoke update = ConfigPoke.builder()
                .id(id)
                .value(value)
                .description(description)
                .build();

        int flag = configPokeService.updateEntity(update);
        return flag > 0;
    }


    public boolean delete(Long id) {
        if (Objects.isNull(id)) {
            throw new BusinessException(PokeExceptionEnum.COMMON_100001);
        }

        int cnt = configPokeService.selectForCount(ConfigPokeQuery.builder()
                .parentId(id)
                .build());
        if (cnt > 0) {
            throw new BusinessException(PokeExceptionEnum.CHILDERN_EXIST_ERR);
        }


        int flag = configPokeService.deleteById(id);
        return flag > 0;
    }


}
