package com.michaelj.infrastructure.enums;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wanzhijun
 */
@Getter
@AllArgsConstructor
public enum FileSuffixEnum {
    /**
     *
     */
    PNG(".png", "PNG"),
    JPG(".jpg", "JPG"),
    ;

    private final String suffix;
    private final String desc;



    public String getFileName(String fileName) {
        if (StrUtil.isBlank(fileName)) {
            return fileName;
        }

        return fileName + this.getSuffix();
    }

}
