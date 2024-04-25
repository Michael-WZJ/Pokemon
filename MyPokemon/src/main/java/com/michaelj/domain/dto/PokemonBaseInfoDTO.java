package com.michaelj.domain.dto;

//import javax.validation.constraints.*;
// 从 JDK 11 开始，Java SE 平台开始逐步淘汰 javax.* 包，并逐渐将其替换为 jakarta.* 包
// https://blog.csdn.net/xu_xin96/article/details/136283250

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class PokemonBaseInfoDTO {
    /**
     * Id
     */
    @NotNull(message = "宝可梦ID 不能为空", groups = ValidateBaseInfo.update.class)
    private Long pokeBaseId;

    /**
     * 编号
     */
    @NotBlank(message = "编号不能为空")
    private String pokeBaseCode;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String pokeBaseName;

    /**
     * 世代
     */
    @NotBlank(message = "世代不能为空")
    private String gen;

    /**
     * 属性1
     */
    @NotBlank(message = "属性不能为空")
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

    /**
     * 进化（编号）
     */
    private String evolution;

    /**
     * 进化前（编号）
     */
    private String filial;
}
