package com.michaelj.domain.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class PropertyDTO {
    /**
     * Id
     */
    private Long propId;

    /**
     * 编号
     */
    @NotBlank(message = "编号不能为空")
    private String propCode;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String propName;

    /**
     * 排序
     */
    private Long propSort;

    /**
     * css基本色
     */
    @NotBlank(message = "css颜色不能为空")
    private String cssColorBase;

    /**
     * css背景色
     */
    @NotBlank(message = "css颜色不能为空")
    private String cssColorBackground;

    /**
     * css边线色
     */
    @NotBlank(message = "css颜色不能为空")
    private String cssColorBorder;
}
