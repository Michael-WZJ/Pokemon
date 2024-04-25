package com.michaelj.domain.entity;

import lombok.Data;

@Data
public class Evolution {
    /**
     * Id
     */
    private Long evolId;

    /**
     * 进化前编号
     */
    private String filialCode;

    /**
     * 进化后编号
     */
    private String paternalCode;
}
