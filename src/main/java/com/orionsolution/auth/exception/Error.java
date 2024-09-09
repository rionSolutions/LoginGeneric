package com.orionsolution.auth.exception;

public record Error(
        String message,
        int status
) {
}
