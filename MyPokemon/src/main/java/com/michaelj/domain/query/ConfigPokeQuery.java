package com.michaelj.domain.query;

import com.michaelj.domain.base.PageParams;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfigPokeQuery extends PageParams {
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

}
