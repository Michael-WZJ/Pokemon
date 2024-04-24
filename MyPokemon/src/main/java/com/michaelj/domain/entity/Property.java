package com.michaelj.domain.entity;

import lombok.Data;

@Data
public class Property {
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

    /**
     * css基本色
     */
    private String cssColorBase;

    /**
     * css背景色
     */
    private String cssColorBackground;

    /**
     * css边线色
     */
    private String cssColorBorder;

}
