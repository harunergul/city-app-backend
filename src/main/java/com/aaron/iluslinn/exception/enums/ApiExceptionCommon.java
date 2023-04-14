package com.aaron.iluslinn.exception.enums;

import com.aaron.iluslinn.exception.ExceptionCode;

import lombok.Getter;

@Getter
public enum ApiExceptionCommon implements ExceptionCode {

    EMPTY_FIELD("Empty Fields");

    private final String key;

    ApiExceptionCommon(String key) {
        this.key = key;
    }
    }
