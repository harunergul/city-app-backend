package com.aaron.iluslinn.exception;

import com.aaron.iluslinn.config.ErrorMessage;
import com.aaron.iluslinn.exception.ApiException;
import com.aaron.iluslinn.exception.ApiRequestException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {


    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleLoginError(Exception exception) {
        log.error("error message :", exception.getMessage());
        ErrorMessage errorMessage = new ErrorMessage(403, "Forbidden", "undefined", exception.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleException(Exception exception) {
        log.error("error message :", exception.getMessage());
        ErrorMessage errorMessage = new ErrorMessage(400, "Bad Request", "undefined", exception.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiException exception = new ApiException(e.getMessage(), httpStatus);
        return new ResponseEntity<>(exception, e.getHttpStatus());
    }
}
