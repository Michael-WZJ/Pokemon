package com.michaelj.service;

import com.michaelj.domain.dto.GenerationDTO;
import com.michaelj.domain.entity.Generation;

import java.util.List;

public interface GenerationService {
    /**
     * 查询所有世代
     * @return
     */
    List<GenerationDTO> getAllGen();
}
