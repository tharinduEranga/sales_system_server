package com.icbt.ap.sales.controller.v1.util;

import java.time.format.DateTimeFormatter;

/**
 * @author Tharindu Eranga
 * @date Tue 09 Feb 2021
 */

public final class ApiConstant {
    private ApiConstant() {
    }

    public static final String VERSION = "/v1";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    public static final class Validation {
        public static final String NAME_REQUIRED = "Name is mandatory";
    }
}
