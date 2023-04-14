package com.aaron.iluslinn.exception.enums;

import com.aaron.iluslinn.exception.ExceptionCode;

import lombok.Getter;

@Getter
public enum ApiExceptionCity implements ExceptionCode {

    INVALID_FIELDS("Error: Invalid Fields");

    private final String key;

    ApiExceptionCity(String key) {
        this.key = key;
    }
    }
