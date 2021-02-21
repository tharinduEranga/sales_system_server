package com.icbt.ap.sales.exception;

import lombok.Getter;

/**
 * @author Tharindu Eranga
 * @date Sun 21 Feb 2021
 */
@Getter
public class CustomAuthException extends RuntimeException {
    private final String code;
    private final String message;
    private final String[] args;

    public CustomAuthException(String code, String message, String[] args) {
        this.code = code;
        this.message = message;
        this.args = args;
    }
}
