package com.orionsolution.authenticator.model;

public record ResourcesPolicyDataDTO(
        String name,
        String surname,
        String email,
        String cellphone,
        String document,
        AuthorizationDTO auth
){}


