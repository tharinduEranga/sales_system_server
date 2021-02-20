package com.icbt.ap.sales.util;

/**
 * @author Tharindu Eranga
 * @date Sat 20 Feb 2021
 */
public class StringUtil {
    private StringUtil() {
    }

    public static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    public static boolean isNotBlank(String value) {
        return !isBlank(value);
    }
}
