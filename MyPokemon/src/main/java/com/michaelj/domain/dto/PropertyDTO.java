package com.michaelj.domain.dto;

import lombok.Data;

@Data
public class PropertyDTO {
    /**
     * Id
     */
    private Long propId;

    /**
     * 编号
     */
    private String propCode;

    /**
     * 名称
     */
    private String propName;

    /**
     * 排序
     */
    private Long propSort;
}
