package com.orionsolution.authenticator.config;

import com.orionsolution.authenticator.model.AuthorizationDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;
import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final SecurityValidationAccessConfig securityValidationAccess;

    public SecurityConfig(SecurityValidationAccessConfig securityValidationAccess) {
        this.securityValidationAccess = securityValidationAccess;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize.requestMatchers("/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html").permitAll().anyRequest().authenticated())
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