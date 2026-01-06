package com.michaelj.infrastructure.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

/**
 * @author wanzhijun
 */
@Getter
@AllArgsConstructor
public enum DatePatternEnum {
    /**
     *
     */
    DATE_PATTERN("yyyyMMdd", "日期"),
    DATE_DASH_PATTERN("yyyy-MM-dd", "日期"),
    DATE_CH_PATTERN("yyyy年MM月dd日", "年月日"),

    MONTH_PATTERN("yyyyMM", "月份"),
    MONTH_DASH_PATTERN("yyyy-MM", "月份"),

    MINUTE_PATTERN("yyyyMMddHHmm", "分钟"),
    FULL_SECOND_PATTERN("yyyy-MM-dd HH:mm:ss", "时分秒"),
    ;

    private final String pattern;
    private final String desc;


    public DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern(this.getPattern());
    }


    public boolean isDatePattern() {
        switch (this) {
            case DATE_PATTERN:
            case DATE_DASH_PATTERN:
            case DATE_CH_PATTERN:
            case MINUTE_PATTERN:
            case FULL_SECOND_PATTERN:
                return true;
            default:
                return false;
        }
    }

    public boolean isMonthPattern() {
        switch (this) {
            case MONTH_PATTERN:
            case MONTH_DASH_PATTERN:
                return true;
            default:
                return false;
        }
    }

}
