package com.aaron.iluslinn.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ApiRequestException extends RuntimeException {


    private ExceptionCode exceptionCode;

    private String[] args;

    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;


    public ApiRequestException(ExceptionCode exceptionCode, String... args) {
        super(exceptionCode.getKey());
        this.exceptionCode = exceptionCode;
        this.args = args;
    }

    public ApiRequestException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
