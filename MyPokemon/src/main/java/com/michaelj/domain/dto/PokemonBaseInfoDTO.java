package com.michaelj.domain.dto;

import javax.validation.Valid;
import javax.validation.constraints.*;

import lombok.Data;

@Data
public class PokemonBaseInfoDTO {
    /**
     * Id
     */
    private Long pokeBaseId;

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
    private String gen;

    /**
     * 属性1
     */
    private String prop1;

    /**
     * 属性2
     */
    private String prop2;

    /**
     * 日文名
     */
    private String nameJpn;

    /**
     * 英文名
     */
    private String nameEng;

    /**
     * 图片路径
     */
    private String pokeBasePic;
}
