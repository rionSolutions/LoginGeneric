package com.orionsolution.authenticator.controller;

import com.orionsolution.authenticator.model.CredentialsDTO;
import com.orionsolution.authenticator.model.ResourcesPolicyDataDTO;
import com.orionsolution.authenticator.service.AuthenticatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    private final AuthenticatorService authenticatorService;

    /**
     * Constructor for LoginController.
     *
     * @param authenticatorService the service for handling authentication logic
     */
    public LoginController(AuthenticatorService authenticatorService) {
        this.authenticatorService = authenticatorService;
    }

    /**
     * Handles the login request.
     *
     * @param credentials a map containing the username and password
     * @return ResponseEntity containing ResourcesPolicyDataDTO and HTTP status
     */
    @Operation(summary = "Handles the login request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful login",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResourcesPolicyDataDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Invalid credentials",
                    content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<ResourcesPolicyDataDTO> login(@RequestParam Map<String, String> credentials) {
        return new ResponseEntity<>(authenticatorService.authenticate(
                new CredentialsDTO(credentials.get("username"), credentials.get("password"))),
                HttpStatus.OK);
    }
}