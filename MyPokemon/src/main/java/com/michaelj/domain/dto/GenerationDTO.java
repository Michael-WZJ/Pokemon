package com.michaelj.domain.dto;

import lombok.Data;

@Data
public class GenerationDTO {
    /**
     * Id
     */
    private Long genId;

    /**
     * 编号
     */
    private String genCode;

    /**
     * 名称
     */
    private String genName;

    /**
     * 排序
     */
    private Long genSort;

    /**
     * 地区
     */
    private String genRegion;
}
