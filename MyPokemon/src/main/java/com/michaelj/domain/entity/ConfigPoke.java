package com.michaelj.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfigPoke {
    /**
     * Id
     */
    private Long id;

    /**
     * 编码
     */
    private String code;

    /**
     * 值
     */
    private String value;

    /**
     * 描述
     */
    private String description;

    /**
     * 父Id
     */
    private Long parentId;

    /**
     * 当前路径
     */
    private String currentPath;

    /**
     * 父路径
     */
    private String parentPath;



    /**
     * 创建时间
     */
    private String createdAt;

    /**
     * 更新时间
     */
    private String updatedAt;

}
