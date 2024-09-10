package com.orionsolution.auth.service.impl;

import com.orionsolution.auth.entity.User;
import com.orionsolution.auth.exception.BusinessException;
import com.orionsolution.auth.model.AuthorizationDTO;
import com.orionsolution.auth.repository.UserRepository;
import com.orionsolution.auth.service.AuthenticatorService;
import com.orionsolution.auth.model.CredentialsDTO;
import com.orionsolution.auth.model.ResourcesPolicyDataDTO;
import com.orionsolution.auth.utility.ApplicationKeyUtility;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatorServiceImpl implements AuthenticatorService {

    private final UserRepository userRepository;

    public AuthenticatorServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResourcesPolicyDataDTO authenticate(CredentialsDTO credentials) {
        User user = userRepository.findByEmail(credentials.username());
        if (user != null && user.isValidate()) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            if (bCryptPasswordEncoder.matches(credentials.password(), user.getPassword())) {
                return new ResourcesPolicyDataDTO(user.getUsername(), user.getSurname(), user.getEmail(), user.getPhone(), user.getDocument(), new AuthorizationDTO(ApplicationKeyUtility.getAuthorization()));
            }
        }
        throw new BusinessException.HandlerException("Invalid credentials or Email not validated", HttpStatus.UNAUTHORIZED);
    }
}
