package com.orionsolution.authenticator.service.impl;

import com.orionsolution.authenticator.entity.User;
import com.orionsolution.authenticator.exception.BusinessException;
import com.orionsolution.authenticator.model.AuthorizationDTO;
import com.orionsolution.authenticator.model.CredentialsDTO;
import com.orionsolution.authenticator.model.ResourcesPolicyDataDTO;
import com.orionsolution.authenticator.repository.UserRepository;
import com.orionsolution.authenticator.service.AuthenticatorService;
import com.orionsolution.authenticator.utility.ApplicationKeyUtility;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service implementation for handling authentication logic.
 */
@Service
public class AuthenticatorServiceImpl implements AuthenticatorService {

    private final UserRepository userRepository;

    /**
     * Constructor for AuthenticatorServiceImpl.
     *
     * @param userRepository the repository for accessing user data
     */
    public AuthenticatorServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Authenticates the user based on provided credentials.
     *
     * @param credentials the credentials provided by the user
     * @return ResourcesPolicyDataDTO containing user information and authorization data
     * @throws BusinessException.HandlerException if the credentials are invalid or the email is not validated
     */
    @Override
    public ResourcesPolicyDataDTO authenticate(CredentialsDTO credentials) {
        User user = userRepository.findByDocument(credentials.username());
        if (user != null && user.isValidate()) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            if (bCryptPasswordEncoder.matches(credentials.password(), user.getPassword())) {
                return new ResourcesPolicyDataDTO(user.getName(), user.getSurname(), user.getEmail(), user.getPhone(), user.getDocument(), new AuthorizationDTO(ApplicationKeyUtility.getAuthorization()));
            }
        }
        throw new BusinessException.HandlerException("Invalid credentials or Email not validated", HttpStatus.UNAUTHORIZED);
    }
}