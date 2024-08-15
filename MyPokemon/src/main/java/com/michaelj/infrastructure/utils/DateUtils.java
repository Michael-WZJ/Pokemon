package com.michaelj.infrastructure.utils;

import cn.hutool.core.util.StrUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
    /**
     * 时区 - 默认
     */
    public static final String TIME_ZONE_DEFAULT = "GMT+8";

    /**
     * 一年的月数
     */
    public static final int MONTH_OF_YEAR = 12;

    public static final String DATE_PATTERN = "yyyyMMdd";
    public static final String MONTH_PATTERN = "yyyyMM";
    public static final String MINUTE_PATTERN = "yyyyMMddHHmm";


    public static String format(LocalDateTime date, String patternTemplate) {
        if (date == null) {
            return "";
        } else {
            String pattern = "yyyy-MM-dd HH:mm:ss";
            if (patternTemplate != null && StrUtil.isNotBlank(patternTemplate)) {
                pattern = patternTemplate;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return date.format(formatter);
        }
    }
}
