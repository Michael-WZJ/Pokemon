package com.michaelj.service.impl;

import com.michaelj.dao.GenerationMapper;
import com.michaelj.domain.converter.GenerationConverter;
import com.michaelj.domain.dto.GenerationDTO;
import com.michaelj.domain.entity.Generation;
import com.michaelj.service.GenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenerationServiceImpl implements GenerationService {
    @Autowired
    private GenerationMapper generationMapper;

    @Autowired
    private GenerationConverter generationConverter;

    /**
     * 查询所有世代
     *
     * @return
     */
    @Override
    public List<GenerationDTO> getAllGen() {
        return generationConverter.toDtoList(generationMapper.getAllGen());
    }
}
