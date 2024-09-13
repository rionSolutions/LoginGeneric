package com.orionsolution.authenticator.config;

import com.orionsolution.authenticator.exception.BusinessException;
import com.orionsolution.authenticator.model.AuthorizationDTO;
import com.orionsolution.authenticator.utility.ApplicationKeyUtility;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@Data
@ConfigurationProperties(prefix = "security")
public class SecurityValidationAccessConfig {

    private String systemOrigin;

    private String requestResource;

    private final RestTemplate restTemplate = new RestTemplate();

    public AuthorizationDTO validateAccess(String token) {
       return getValidationFromAccessToken(token);
    }

    private AuthorizationDTO getValidationFromAccessToken(String token) {
        HttpEntity<String> entity = getStringHttpEntity(token);
        ResponseEntity<AuthorizationDTO> response =
                restTemplate.exchange(requestResource, HttpMethod.GET, entity, AuthorizationDTO.class);
        ApplicationKeyUtility.setAuthorization(Objects.requireNonNull(response.getBody()).access_token());

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new BusinessException.HandlerException("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        return response.getBody();
    }


    private @NotNull HttpEntity<String> getStringHttpEntity(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.set("Content-Type", "application/json");
        headers.set("systemOrigin", systemOrigin);
        return new HttpEntity<>(headers);
    }
}
