package com.orionsolution.authenticator.service;

import com.orionsolution.authenticator.model.CredentialsDTO;
import com.orionsolution.authenticator.model.ResourcesPolicyDataDTO;

public interface AuthenticatorService {

    ResourcesPolicyDataDTO authenticate(CredentialsDTO credentials);
}
