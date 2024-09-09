package com.orionsolution.auth.exception;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.Getter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.Serial;


@ControllerAdvice
public class BusinessException extends RuntimeException {

    @Getter
    public static class HandlerException extends RuntimeException {
        @Serial
        private static final long serialVersionUID = 1L;
        private final String message;
        private final HttpStatus status;

        public HandlerException(String message, HttpStatus status) {
            this.message = message;
            this.status = status;
        }

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> businessException(Exception ex) {
        return new ResponseEntity<>(new Error(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.value()), HttpStatus.UNPROCESSABLE_ENTITY);
    }


}
