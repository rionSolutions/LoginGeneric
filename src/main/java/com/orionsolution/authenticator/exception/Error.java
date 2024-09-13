package com.orionsolution.authenticator.exception;

public record Error(
        String message,
        int status
) {
}
