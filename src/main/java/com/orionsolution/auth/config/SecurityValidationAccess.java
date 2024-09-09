package com.orionsolution.auth.config;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Data
@ConfigurationProperties(prefix = "security")
public class SecurityValidationAccess {

    private String systemOrigin;

    private String requestResource;

    private final RestTemplate restTemplate = new RestTemplate();

    public Object validateAccess(String token) {
        HttpEntity<String> entity = getStringHttpEntity(token);
        Object response = restTemplate.exchange(requestResource, HttpMethod.GET, entity, Object.class).getBody();

        //TODO TRATAR RETORNO
        return null;
    }

    private @NotNull HttpEntity<String> getStringHttpEntity(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.set("Content-Type", "application/json");
        headers.set("systemOrigin", systemOrigin);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return entity;
    }
}
