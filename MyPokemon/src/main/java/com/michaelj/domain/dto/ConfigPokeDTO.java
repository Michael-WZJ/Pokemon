package com.michaelj.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfigPokeDTO {
    /**
     * Id
     */
    @NotNull(message = "配置ID 不能为空", groups = ValidateConfig.update.class)
    private Long id;

    /**
     * 编码
     */
    @NotBlank(message = "编码 不能为空", groups = ValidateConfig.add.class)
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
    @NotNull(message = "父Id 不能为空", groups = ValidateConfig.add.class)
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
