package com.orionsolution.auth.controller;

import com.orionsolution.auth.model.CredentialsDTO;
import com.orionsolution.auth.model.ResourcesPolicyDataDTO;
import com.orionsolution.auth.service.AuthenticatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    private final AuthenticatorService authenticatorService;

    public LoginController(AuthenticatorService authenticatorService) {
        this.authenticatorService = authenticatorService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<ResourcesPolicyDataDTO> login(@RequestParam Map<String, String> credentials) {
        authenticatorService.authenticate(new CredentialsDTO(credentials.get("username"), credentials.get("password")));
        return  new ResponseEntity<>(HttpStatus.OK);
    }


}