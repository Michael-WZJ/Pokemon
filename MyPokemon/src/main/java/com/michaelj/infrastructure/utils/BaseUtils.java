package com.michaelj.infrastructure.utils;

public class BaseUtils {
    public static String generatePrevCode(String code, boolean withDash) {
        return null;
    }

    /**
     * String.format()
     * %：开始格式化占位符。
     * 0：表示如果数字不足位数，则用0填充。
     * n：表示所需的最小总位数，这里是变量n的值。
     * d：表示格式化为十进制整数。
     * @param code
     * @param add
     * @return
     */
    public static String computeCode(String code, boolean add) {
        int len = code.length();
        int value = Integer.parseInt(code);

        value = value + (add ? 1 : -1);

        return buildCode(value, len);
    }

    public static String buildCode(int value, int len) {
        return String.format("%0" + len + "d", value);
    }
}
