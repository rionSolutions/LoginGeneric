package com.orionsolution.auth.config;

import com.orionsolution.auth.model.AuthorizationDTO;
import jakarta.servlet.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {

    private final SecurityValidationAccessConfig securityValidationAccess;

    public SecurityConfig(SecurityValidationAccessConfig securityValidationAccess) {
        this.securityValidationAccess = securityValidationAccess;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt())
                .authenticationManager(authentication -> {
                    String token = (String) authentication.getPrincipal();
                    AuthorizationDTO auth = securityValidationAccess.validateAccess(token);
                    if (auth != null) {
                        List<GrantedAuthority> authorities =
                                List.of(new SimpleGrantedAuthority("ROLE_USER"));
                        return new UsernamePasswordAuthenticationToken(auth.access_token(), null, authorities);
                    }
                    throw new BadCredentialsException("Invalid Token requested!");
                });
        return httpSecurity.build();
    }


}