package com.icbt.ap.sales.exception;

import lombok.Getter;

/**
 * @author Tharindu Eranga
 * @date Mon 08 Feb 2021
 */
@Getter
public class CustomServiceException extends RuntimeException {
    private final String code;
    private final String message;
    private final Throwable throwable;

    public CustomServiceException(String code, String message) {
        this.code = code;
        this.message = message;
        this.throwable = null;
    }

    public CustomServiceException(String code, String message, Throwable throwable) {
        this.code = code;
        this.message = message;
        this.throwable = throwable;
    }

}
