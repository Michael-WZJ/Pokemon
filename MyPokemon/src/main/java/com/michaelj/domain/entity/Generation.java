package com.michaelj.domain.entity;

import lombok.Data;

@Data
public class Generation {
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
