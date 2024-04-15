package com.michaelj.domain.query;

import com.michaelj.domain.base.PageParams;
import lombok.Data;

import java.util.List;

/**
 * 宝可梦基本信息查询对象
 */
@Data
public class PokeBaseInfoQuery extends PageParams {
    /**
     * 编号
     */
    private String pokeBaseCode;

    /**
     * 名称
     */
    private String pokeBaseName;

    /**
     * 世代
     */
    private List<String> gen;

    /**
     * 属性1
     */
    private String prop1;

    /**
     * 属性2
     */
    private String prop2;
}
