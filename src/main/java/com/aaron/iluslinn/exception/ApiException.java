package com.aaron.iluslinn.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ApiException {

    private final Integer status;
    private final String message;

    public ApiException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.status = httpStatus.value();
    }

}
